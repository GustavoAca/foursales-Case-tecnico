package com.foursales.foursale_desafio.domain.mapper.dto;

import com.foursales.foursale_desafio.domain.core.domain.model.dto.DtoAbstract;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
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
    private Usuario usuario;
    @Builder.Default
    private List<ProdutoPedidoDto> produtosPedidos = new LinkedList<>();

    public UUID getUsuarioId() {
        return this.usuario.getId();
    }
}
