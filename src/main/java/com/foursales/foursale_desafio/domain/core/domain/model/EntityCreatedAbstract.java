package com.foursales.foursale_desafio.domain.core.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class EntityCreatedAbstract {

    @Column(name = "data_de_criacao", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataDeCriacao;

    @Column(name = "criado_por", nullable = false, updatable = false)
    @CreatedBy
    private String criadoPor;
}
