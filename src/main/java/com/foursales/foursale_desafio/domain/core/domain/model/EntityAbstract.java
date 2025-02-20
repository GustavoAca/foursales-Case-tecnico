package com.foursales.foursale_desafio.domain.core.domain.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class EntityAbstract extends EntityCreatedAbstract {

    @Column(name = "data_de_modificacao")
    @LastModifiedDate
    private LocalDateTime dataDeModificacao;

    @Column(name = "modificado_por")
    @LastModifiedBy
    private String modificado_por;

    @Access(AccessType.PROPERTY)
    public void setModifiedDate(LocalDateTime dataDeModificacao) {
        this.dataDeModificacao = dataDeModificacao;
    }

    @Access(AccessType.PROPERTY)
    public void setModifiedBy(String modificado_por) {
        this.modificado_por = modificado_por;
    }
}
