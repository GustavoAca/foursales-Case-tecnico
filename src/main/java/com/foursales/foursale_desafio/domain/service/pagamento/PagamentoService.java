package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PagamentoService extends BaseService<Pagamento, UUID> {

    Boolean realizarPagamento(UUID pedidoId);

    PagamentoDto buscaPorId(UUID id);

    ResponsePage<PagamentoDto> listarPaginado(Pageable pageable);
}
