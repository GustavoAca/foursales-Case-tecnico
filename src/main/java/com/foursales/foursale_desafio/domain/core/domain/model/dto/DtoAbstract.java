package com.foursales.foursale_desafio.domain.core.domain.model.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DtoAbstract {

    private LocalDateTime dataDeModificacao;
    private String modificadoPor;
    private LocalDateTime dataDeCriacao;
    private String criadoPor;
}
