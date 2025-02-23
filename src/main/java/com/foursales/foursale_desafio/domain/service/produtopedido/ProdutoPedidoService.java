package com.foursales.foursale_desafio.domain.service.produtopedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProdutoPedidoService extends BaseService<ProdutoPedido, UUID> {

    ProdutoPedidoDto criar(ProdutoPedidoDto produtoDto);

    ResponsePage<ProdutoPedidoDto> listarPaginado(Pageable pageable);

    ResponsePage<ProdutoPedidoDto> listarPaginadoPorPedidoId(UUID pedidoId, Pageable pageable);

    ProdutoPedidoDto buscaPorId(UUID id);

    ProdutoPedidoDto atualizar(UUID id, ProdutoPedidoDto produtoDto);

    int atualizarEstoque(UUID pedidoId);

    void deletarPorId(UUID id);
}
