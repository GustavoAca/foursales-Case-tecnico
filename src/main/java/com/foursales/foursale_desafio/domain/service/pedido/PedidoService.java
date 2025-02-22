package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.pedido.GastoMedioUsuario;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PedidoService extends BaseService<Pedido, UUID> {
    PedidoDto criar(PedidoDto pedidoDto);

    ResponsePage<PedidoDto> listarPaginado(Pageable pageable);

    ResponsePage<PedidoDto> listarPorUsuarioIdPaginado(UUID usuarioId, Pageable pageable);

    PedidoDto buscaPorId(UUID id);

    PedidoDto atualizar(UUID id, PedidoDto pedidoDto);

    Boolean isTodosProdutosPedidosTemEstoque(UUID id);

    PedidoDto adicionarProduto(List<ProdutoPedidoDto> produtoPedidoDtos, UUID id);

    void atualizarStatus(UUID id);

    ResponsePage<GastoMedioUsuario> getValorGastoMedioPorUsuario(Pageable pageable);
}
