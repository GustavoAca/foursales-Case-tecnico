package com.foursales.foursale_desafio.domain.repository.projection;

import java.math.BigDecimal;

public interface FaturamentoProjection {

    Integer getAno();

    Integer getMes();

    BigDecimal getFaturamento();
}
