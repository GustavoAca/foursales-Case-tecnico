package com.foursales.foursale_desafio.domain.service.analise;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.model.pedido.GastoMedioUsuario;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnaliseServiceImpl {

    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public AnaliseServiceImpl(UsuarioService usuarioService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public ResponsePage<Usuario> getMaioresCompradores(Pageable pageable) {
        return usuarioService.getMaioresCompradores(pageable);
    }

    public ResponsePage<GastoMedioUsuario> getValorGastoMedioPorUsuario(Pageable pageable) {
        return pedidoService.getValorGastoMedioPorUsuario(pageable);
    }
}
