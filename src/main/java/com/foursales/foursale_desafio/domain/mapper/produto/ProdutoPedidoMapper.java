package com.foursales.foursale_desafio.domain.mapper.produto;

import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.pedido.PedidoMapper;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProdutoPedidoMapper {

    private final PedidoMapper pedidoMapper;
    private final ProdutoMapper produtoMapper;

    public ProdutoPedidoMapper(PedidoMapper pedidoMapper,
                               ProdutoMapper produtoMapper) {
        this.pedidoMapper = pedidoMapper;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoPedido toEntity(ProdutoPedidoDto produtoPedidoDto) {
        return ProdutoPedido.builder()
                .id(Objects.isNull(produtoPedidoDto.getId()) ? null : produtoPedidoDto.getId())
                .preco(produtoPedidoDto.getPreco())
                .quantidade(produtoPedidoDto.getQuantidade())
                .pedido(toPedidoEntity(produtoPedidoDto.getPedido()))
                .produto(toProdutoEntity(produtoPedidoDto.getProduto()))
                .estoqueDisponivel(produtoPedidoDto.getEstoqueDisponivel())
                .criadoPor(produtoPedidoDto.getCriadoPor())
                .dataDeCriacao(produtoPedidoDto.getDataDeCriacao())
                .modificadoPor(produtoPedidoDto.getModificadoPor())
                .dataDeModificacao(produtoPedidoDto.getDataDeModificacao())
                .build();
    }

    private Pedido toPedidoEntity(PedidoDto pedidoDto) {
        return Objects.isNull(pedidoDto) ? null : pedidoMapper.toEntity(pedidoDto);
    }

    private Produto toProdutoEntity(ProdutoDto produtoDto) {
        return Objects.isNull(produtoDto) ? null : produtoMapper.toEntity(produtoDto);
    }

    public ProdutoPedidoDto toDto(ProdutoPedido produtoPedido) {
        return ProdutoPedidoDto.builder()
                .id(produtoPedido.getId())
                .preco(produtoPedido.getPreco())
                .quantidade(produtoPedido.getQuantidade())
                .pedido(toPedidoDto(produtoPedido.getPedido()))
                .produto(toProdutoDto(produtoPedido.getProduto()))
                .estoqueDisponivel(produtoPedido.getEstoqueDisponivel())
                .criadoPor(produtoPedido.getCriadoPor())
                .dataDeCriacao(produtoPedido.getDataDeCriacao())
                .modificadoPor(produtoPedido.getModificadoPor())
                .dataDeModificacao(produtoPedido.getDataDeModificacao())
                .build();
    }

    private PedidoDto toPedidoDto(Pedido pedido) {
        return Objects.isNull(pedido) ? null : pedidoMapper.toDto(pedido);
    }

    private ProdutoDto toProdutoDto(Produto produto) {
        return Objects.isNull(produto) ? null : produtoMapper.toDto(produto);
    }
}
