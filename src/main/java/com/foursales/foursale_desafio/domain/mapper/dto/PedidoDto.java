package com.foursales.foursale_desafio.domain.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PedidoDto extends DtoAbstract {

    private UUID id;
    @Builder.Default
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @Builder.Default
    private Status status = Status.PENDENTE;
    private UsuarioDto usuario;
    @Builder.Default
    private List<ProdutoPedidoDto> produtosPedidos = new LinkedList<>();

    @JsonIgnore
    public UUID getUsuarioId() {
        return this.usuario.getId();
    }
}
