package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.pedido.PedidoMapper;
import com.foursales.foursale_desafio.domain.model.pedido.GastoMedioUsuario;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.repository.PedidoRepository;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.exception.ProdutoSemEstoqueException;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
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

    protected PedidoServiceImpl(PedidoRepository repo,
                                PedidoMapper pedidoMapper,
                                ProdutoService produtoService) {
        super(repo);
        this.pedidoMapper = pedidoMapper;
        this.produtoService = produtoService;
    }

    public PedidoDto criar(PedidoDto pedidoDto) {
        return pedidoMapper.toDto(salvar(pedidoMapper.toEntity(pedidoDto)));
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

    @Override
    public Boolean isTodosProdutosPedidosTemEstoque(UUID id) {

        return Boolean.FALSE;
    }

    public PedidoDto adicionarProduto(List<ProdutoPedidoDto> produtoPedidoDtos, UUID id) {
        PedidoDto pedido = buscaPorId(id);
        List<ProdutoDto> produtosSemEstoque = new LinkedList<>();

        for (ProdutoPedidoDto produtoPedidoDto : produtoPedidoDtos) {
            ProdutoDto produto = produtoService.buscaPorId(produtoPedidoDto.getId());
            if (produto.getQuantidadeEmEstoque() > 0) {
                pedido.setValorTotal(pedido.getValorTotal()
                        .add(produtoPedidoDto.getPreco()
                                .multiply(BigDecimal
                                        .valueOf(produtoPedidoDto.getQuantidade()))));
                produtoPedidoDto.getPedido().setId(id);
            } else {
                produtosSemEstoque.add(produto);
            }
        }

        if (!produtosSemEstoque.isEmpty()) {
            throw new ProdutoSemEstoqueException(produtosSemEstoque.stream().map(ProdutoDto::getNome).toList());
        }

        return pedidoMapper.toDto(salvar(pedidoMapper.toEntity(pedido)));
    }

    @Override
    public void atualizarStatus(UUID id) {

    }

    @Override
    public ResponsePage<GastoMedioUsuario> getValorGastoMedioPorUsuario(Pageable pageable) {
        return mapearPageSimples(repo.findValorGastoMedioPorUsuario(pageable));
    }

    public void removerProduto(List<UUID> produtosPedidosId) {
        produtosPedidosId.forEach(this::deletar);
    }
}
