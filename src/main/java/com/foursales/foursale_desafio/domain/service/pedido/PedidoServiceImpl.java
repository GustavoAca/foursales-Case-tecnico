package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.core.utils.SecurityContextUtils;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.mapper.pedido.PedidoMapper;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.repository.PedidoRepository;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import com.foursales.foursale_desafio.exception.DeletarRegistroException;
import com.foursales.foursale_desafio.exception.ProdutoSemEstoqueException;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, UUID, PedidoRepository> implements PedidoService {

    private final PedidoMapper pedidoMapper;
    private final ProdutoService produtoService;
    private final ProdutoPedidoService produtoPedidoService;

    protected PedidoServiceImpl(PedidoRepository repo,
                                PedidoMapper pedidoMapper,
                                ProdutoService produtoService,
                                ProdutoPedidoService produtoPedidoService) {
        super(repo);
        this.pedidoMapper = pedidoMapper;
        this.produtoService = produtoService;
        this.produtoPedidoService = produtoPedidoService;
    }

    //TODO
    public PedidoDto criar(PedidoDto pedidoDto) {
        List<ProdutoPedidoDto> produtosPedidos = pedidoDto.getProdutosPedidos();
        pedidoDto.setUsuario(UsuarioDto.builder().id(SecurityContextUtils.getId()).build());
        pedidoDto = pedidoMapper.toDto(salvar(pedidoMapper.toEntity(pedidoDto)));
        for (ProdutoPedidoDto produtoPedidoDto : produtosPedidos) {
            if (!produtoService.hasEstoqueDisponivel(produtoPedidoDto.getProdutoId(), produtoPedidoDto.getQuantidade())) {
                pedidoDto = atualizarStatus(pedidoDto.getId(), Status.CANCELADO_AUTOMATICAMENTE);
            }
            calcularValorTotal(pedidoDto, produtoPedidoDto);
            produtoPedidoDto.setPedido(pedidoDto);
            produtoPedidoService.criar(produtoPedidoDto);
        }

        return pedidoDto;
    }

    public ResponsePage<PedidoDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), pedidoMapper::toDto);
    }

    public ResponsePage<PedidoDto> listarPorUsuarioIdPaginado(UUID usuarioId, Pageable pageable) {
        return mapearPage(repo.findByUsuarioId(usuarioId, pageable), pedidoMapper::toDto);
    }

    public PedidoDto buscaPorId(UUID id) {
        Pedido pedido = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, PedidoDto.class.getName()));
        return pedidoMapper.toDto(pedido);
    }

    public PedidoDto atualizar(UUID id, PedidoDto pedidoDto) {
        return buscarPorId(id).map(pedido -> {
            pedidoDto.setId(pedido.getId());
            Pedido produtoAtualizado = salvar(pedidoMapper.toEntity(pedidoDto));
            return pedidoMapper.toDto(produtoAtualizado);
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id, PedidoDto.class.getName()));
    }

    public void adicionarProduto(List<ProdutoPedidoDto> produtoPedidoDtos, UUID id) {
        PedidoDto pedido = buscaPorId(id);
        List<ProdutoDto> produtosSemEstoque = new LinkedList<>();

        for (ProdutoPedidoDto produtoPedidoDto : produtoPedidoDtos) {
            Boolean hasProdutoEstoqueDisponivel = produtoService.hasEstoqueDisponivel(produtoPedidoDto.getProdutoId(), produtoPedidoDto.getQuantidade());
            if (!hasProdutoEstoqueDisponivel) {
                produtosSemEstoque.add(produtoPedidoDto.getProduto());
            }
            calcularValorTotal(pedido, produtoPedidoDto);
            produtoPedidoDto.setPedido(pedido);
            produtoPedidoService.criar(produtoPedidoDto);
        }

        if (!produtosSemEstoque.isEmpty()) {
            atualizarStatus(pedido.getId(), Status.CANCELADO_AUTOMATICAMENTE);
            throw new ProdutoSemEstoqueException(produtosSemEstoque.stream().map(ProdutoDto::getNome).toList());
        }
    }

    private void calcularValorTotal(PedidoDto pedido, ProdutoPedidoDto produtoPedidoDto) {
        pedido.setValorTotal(pedido.getValorTotal()
                .add(produtoPedidoDto.getPreco()
                        .multiply(BigDecimal
                                .valueOf(produtoPedidoDto.getQuantidade()))));
        atualizar(pedido.getId(), pedido);
    }

    @Override
    public PedidoDto atualizarStatus(UUID id, Status status) {
        PedidoDto pedido = buscaPorId(id);
        pedido.setStatus(status);
        return pedidoMapper.toDto(salvar(pedidoMapper.toEntity(pedido)));
    }

    @Override
    public ResponsePage<GastoMedioUsuarioProjection> getValorGastoMedioPorUsuario(Pageable pageable) {
        return mapearPageSimples(repo.findValorGastoMedioPorUsuario(pageable));
    }

    @Override
    public GastoMedioUsuarioProjection getValorGastoMedioPorUsuarioId(UUID usuarioId) {
        return repo.findValorGastoMedioPorUsuarioId(usuarioId);
    }

    @Override
    public Boolean isDisponivelParaPagamento(UUID id) {
        int numeroPagina = 0;
        int pageSize = 10;
        boolean hasMaisPaginas;

        do {
            Page<ProdutoPedidoDto> produtosPedidos = produtoPedidoService.listarPaginadoPorPedidoId(id, PageRequest.of(numeroPagina, pageSize));

            for (ProdutoPedidoDto produtoPedido : produtosPedidos.getContent()) {
                Boolean hasEstoqueDisponivel = produtoService.hasEstoqueDisponivel(produtoPedido.getProdutoId(), produtoPedido.getQuantidade());
                if (!hasEstoqueDisponivel) {
                    return Boolean.FALSE;
                }
            }

            hasMaisPaginas = produtosPedidos.hasNext();
            numeroPagina++;
        } while (hasMaisPaginas);

        return getStatus(id).equals(Status.PENDENTE);
    }

    private Status getStatus(UUID id) {
        return repo.findStatusById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, PedidoDto.class.getName()))
                .getStatus();
    }

    public void removerProduto(List<UUID> produtosPedidosId) {
        try {
            produtosPedidosId.forEach(this::deletar);
        } catch (DeletarRegistroException e) {
            throw new DeletarRegistroException("Erro em deletar produtos com os ids %s", produtosPedidosId);
        }
    }

    public ResponsePage<FaturamentoProjection> getFaturamentoPorPerido(int mesReferencia,
                                                                       int anoReferencia,
                                                                       Pageable pageable) {
        return mapearPageSimples(repo.findByFaturamentoMensal(mesReferencia, anoReferencia, pageable));
    }
}
