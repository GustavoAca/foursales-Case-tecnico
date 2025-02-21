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
    private BigDecimal valorTotal;
    @Builder.Default
    private Status status = Status.PENDENTE;
    @Builder.Default
    private List<ProdutoDto> produtos = new LinkedList<>();
    private Usuario usuario;
}
