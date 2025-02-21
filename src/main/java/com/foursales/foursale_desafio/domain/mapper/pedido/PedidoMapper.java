package com.foursales.foursale_desafio.domain.mapper.pedido;

import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    private final ProdutoMapper produtoMapper;

    public PedidoMapper(ProdutoMapper produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    public Pedido toEntity(PedidoDto pedidoDto) {
        return Pedido.builder()
                .id(pedidoDto.getId())
                .valorTotal(pedidoDto.getValorTotal())
                .status(pedidoDto.getStatus())
                .produtos(toProdutoEntity(pedidoDto.getProdutos()))
                .usuario(pedidoDto.getUsuario())
                .build();
    }

    private List<Produto> toProdutoEntity(List<ProdutoDto> produtosDto) {
        return produtosDto.stream().map(produtoMapper::toEntity).toList();
    }

    public PedidoDto toDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .produtos(toProdutoDto(pedido.getProdutos()))
                .usuario(pedido.getUsuario())
                .build();
    }

    private List<ProdutoDto> toProdutoDto(List<Produto> produtos) {
        return produtos.stream().map(produtoMapper::toDto).toList();
    }
}
