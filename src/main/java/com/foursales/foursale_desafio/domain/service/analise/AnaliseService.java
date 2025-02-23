package com.foursales.foursale_desafio.domain.service.analise;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AnaliseService {

    ResponsePage<UsuarioDto> getMaioresCompradores(Pageable pageable);

    ResponsePage<GastoMedioUsuarioProjection> getValorGastoMedioPorUsuario(Pageable pageable);

    GastoMedioUsuarioProjection getValorGastoMedioPorUsuarioId(UUID usuarioId);

    ResponsePage<FaturamentoProjection> getFaturamentoPorPeriodo(int mesReferencia,
                                                                 int anoReferencia,
                                                                 Pageable pageable);
}
