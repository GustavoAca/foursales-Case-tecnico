package com.foursales.foursale_desafio.domain.repository.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface GastoMedioUsuarioProjection {

    BigDecimal getMediaDeGasto();

    UUID getUsuarioId();
}

