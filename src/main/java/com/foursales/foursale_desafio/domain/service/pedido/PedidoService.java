package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PedidoService extends BaseService<Pedido, UUID> {

    PedidoDto criar(PedidoDto pedidoDto);

    ResponsePage<PedidoDto> listarPaginado(Pageable pageable);

    ResponsePage<PedidoDto> listarPorUsuarioIdPaginado(UUID usuarioId, Pageable pageable);

    PedidoDto buscaPorId(UUID id);

    PedidoDto atualizar(UUID id, PedidoDto pedidoDto);

    void adicionarProduto(List<ProdutoPedidoDto> produtoPedidoDtos, UUID id);

    PedidoDto atualizarStatus(UUID id, Status status);

    ResponsePage<GastoMedioUsuarioProjection> getValorGastoMedioPorUsuario(Pageable pageable);

    GastoMedioUsuarioProjection getValorGastoMedioPorUsuarioId(UUID usuarioId);

    Boolean isDisponivelParaPagamento(UUID id);

    void removerProduto(List<UUID> produtosPedidosId);

    ResponsePage<FaturamentoProjection> getFaturamentoPorPerido(int mesReferencia,
                                                                int anoReferencia,
                                                                Pageable pageable);

}
