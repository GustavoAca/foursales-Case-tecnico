package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;

import java.util.UUID;

public interface PagamentoService extends BaseService<Pagamento, UUID> {

    Boolean realizarPagamento(UUID pedidoId);
}
