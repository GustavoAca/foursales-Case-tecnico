package com.foursales.foursale_desafio.domain.mapper.dto;

import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import com.foursales.foursale_desafio.domain.core.security.Perfil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioDto extends DtoAbstract {

    private UUID id;
    private String email;
    private String senha;
    private Perfil perfil;
    private String nome;
    @Builder.Default
    private Integer totalDeComprasRealizadas = 0;
}
