package com.foursales.foursale_desafio.domain.mapper.dto;

import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PagamentoDto extends DtoAbstract {

    private UUID pedidoId;
}
