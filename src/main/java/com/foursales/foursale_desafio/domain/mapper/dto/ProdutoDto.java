package com.foursales.foursale_desafio.domain.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProdutoDto extends DtoAbstract {

    private UUID id;
    private String descricao;
    private String nome;
    private BigDecimal preco;
    private Integer quantidadeEmEstoque;
    private SubcategoriaDto subcategoria;

    @JsonIgnore
    public UUID getSubcategoriaId() {
        return this.subcategoria.getId();
    }
}
