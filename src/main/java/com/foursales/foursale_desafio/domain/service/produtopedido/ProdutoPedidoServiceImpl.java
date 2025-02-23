package com.foursales.foursale_desafio.domain.service.produtopedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoPedidoMapper;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import com.foursales.foursale_desafio.domain.repository.ProdutoPedidoRepository;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoPedidoServiceImpl extends BaseServiceImpl<ProdutoPedido, UUID, ProdutoPedidoRepository> implements ProdutoPedidoService {

    private final ProdutoPedidoMapper produtoPedidoMapper;
    private final ProdutoService produtoService;

    protected ProdutoPedidoServiceImpl(ProdutoPedidoRepository repo,
                                       ProdutoPedidoMapper produtoPedidoMapper,
                                       ProdutoService produtoService) {
        super(repo);
        this.produtoPedidoMapper = produtoPedidoMapper;
        this.produtoService = produtoService;
    }

    public ProdutoPedidoDto criar(ProdutoPedidoDto produtoPedidoDto) {
        produtoPedidoDto.setProduto(produtoService.buscaPorId(produtoPedidoDto.getProdutoId()));
        return produtoPedidoMapper.toDto(salvar(produtoPedidoMapper.toEntity(produtoPedidoDto)));
    }

    public ResponsePage<ProdutoPedidoDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), produtoPedidoMapper::toDto);
    }

    @Override
    public ResponsePage<ProdutoPedidoDto> listarPaginadoPorPedidoId(UUID pedidoId, Pageable pageable) {
        return mapearPage(repo.findByPedidoId(pedidoId, pageable), produtoPedidoMapper::toDto);
    }

    public ProdutoPedidoDto buscaPorId(UUID id) {
        ProdutoPedido produtoPedido = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, "Produto pedido"));
        return produtoPedidoMapper.toDto(produtoPedido);
    }

    public ProdutoPedidoDto atualizar(UUID id, ProdutoPedidoDto produtoPedidoDto) {
        return buscarPorId(id).map(produtoPedido -> {
            produtoPedidoDto.setId(produtoPedido.getId());
            ProdutoPedido produtoPedidoAtualizado = salvar(produtoPedidoMapper.toEntity(produtoPedidoDto));
            return produtoPedidoMapper.toDto(produtoPedidoAtualizado);
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id, "Produto pedido"));
    }

    @Override
    public int atualizarEstoque(UUID pedidoId) {
        int numeroPagina = 0;
        int pageSize = 10;
        boolean hasMaisPaginas;
        int quantidadeDeItensComprados = 0;

        do {
            Page<ProdutoPedidoDto> produtosPedidos = listarPaginadoPorPedidoId(pedidoId, PageRequest.of(numeroPagina, pageSize));

            for (ProdutoPedidoDto produtoPedido : produtosPedidos.getContent()) {
                produtoPedido.getProduto()
                        .setQuantidadeEmEstoque(produtoPedido.getProduto()
                                .getQuantidadeEmEstoque() - produtoPedido.getQuantidade());
                quantidadeDeItensComprados += produtoPedido.getQuantidade();
                produtoService.atualizar(produtoPedido.getProdutoId(), produtoPedido.getProduto());
            }

            hasMaisPaginas = produtosPedidos.hasNext();
            numeroPagina++;
        } while (hasMaisPaginas);

        return quantidadeDeItensComprados;
    }

    @Override
    public void deletarPorId(UUID id) {
        deletar(id);
    }
}
