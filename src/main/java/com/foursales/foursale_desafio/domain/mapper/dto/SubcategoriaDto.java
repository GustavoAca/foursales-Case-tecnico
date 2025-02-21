package com.foursales.foursale_desafio.domain.mapper.dto;

import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SubcategoriaDto extends DtoAbstract {

    private UUID id;
    private String descricao;
    private String nome;
    private CategoriaDto categoria;
}
