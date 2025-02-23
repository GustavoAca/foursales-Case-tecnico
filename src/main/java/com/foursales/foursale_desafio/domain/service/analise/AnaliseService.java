package com.foursales.foursale_desafio.domain.service.analise;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import org.springframework.data.domain.Pageable;

public interface AnaliseService {

    ResponsePage<Usuario> getMaioresCompradores(Pageable pageable);

    ResponsePage<GastoMedioUsuarioProjection> getValorGastoMedioPorUsuario(Pageable pageable);

    ResponsePage<FaturamentoProjection> getFaturamentoMensal(int mesReferencia,
                                                             int anoReferencia,
                                                             Pageable pageable);
}
