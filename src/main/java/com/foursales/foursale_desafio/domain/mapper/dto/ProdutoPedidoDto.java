package com.foursales.foursale_desafio.domain.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProdutoPedidoDto extends DtoAbstract {

    private UUID id;
    private PedidoDto pedido;
    private ProdutoDto produto;
    private Integer quantidade;
    @Builder.Default
    private BigDecimal preco = BigDecimal.ZERO;
    @Builder.Default
    private Boolean estoqueDisponivel = Boolean.TRUE;

    @JsonIgnore
    public UUID getProdutoId() {
        return this.produto.getId();
    }
}
