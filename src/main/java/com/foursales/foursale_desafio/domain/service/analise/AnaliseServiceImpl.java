package com.foursales.foursale_desafio.domain.service.analise;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnaliseServiceImpl implements AnaliseService {

    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public AnaliseServiceImpl(UsuarioService usuarioService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public ResponsePage<Usuario> getMaioresCompradores(Pageable pageable) {
        return usuarioService.getMaioresCompradores(pageable);
    }

    public ResponsePage<GastoMedioUsuarioProjection> getValorGastoMedioPorUsuario(Pageable pageable) {
        return pedidoService.getValorGastoMedioPorUsuario(pageable);
    }

    public ResponsePage<FaturamentoProjection> getFaturamentoMensal(int mesReferencia,
                                                                    int anoReferencia,
                                                                    Pageable pageable) {
        return pedidoService.getFaturamentoPorPerido(mesReferencia, anoReferencia, pageable);
    }
}
