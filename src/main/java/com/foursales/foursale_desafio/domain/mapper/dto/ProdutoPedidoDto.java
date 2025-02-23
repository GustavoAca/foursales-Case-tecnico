package com.foursales.foursale_desafio.domain.mapper.dto;

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
    private Boolean estoqueDisponivel;

    public UUID getProdutoId() {
        return this.produto.getId();
    }
}
