package com.foursales.foursale_desafio.domain.mapper.pagamento;

import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoDto pagamentoDto) {
        return Pagamento.builder()
                .pedidoId(pagamentoDto.getPedidoId())
                .criadoPor(pagamentoDto.getCriadoPor())
                .dataDeCriacao(pagamentoDto.getDataDeCriacao())
                .modificadoPor(pagamentoDto.getModificadoPor())
                .dataDeModificacao(pagamentoDto.getDataDeModificacao())
                .build();
    }

    public PagamentoDto toDto(Pagamento pagamento) {
        return PagamentoDto.builder()
                .pedidoId(pagamento.getPedidoId())
                .criadoPor(pagamento.getCriadoPor())
                .dataDeCriacao(pagamento.getDataDeCriacao())
                .modificadoPor(pagamento.getModificadoPor())
                .dataDeModificacao(pagamento.getDataDeModificacao())
                .build();
    }
}
