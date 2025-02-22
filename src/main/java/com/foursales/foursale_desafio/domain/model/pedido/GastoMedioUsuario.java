package com.foursales.foursale_desafio.domain.model.pedido;

import java.math.BigDecimal;
import java.util.UUID;

public record GastoMedioUsuario(UUID usuarioId,
                                BigDecimal gastoMedio) {
}
