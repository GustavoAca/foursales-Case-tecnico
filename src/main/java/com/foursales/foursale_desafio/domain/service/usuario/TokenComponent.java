package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.controller.dto.LoginRequest;
import com.foursales.foursale_desafio.controller.dto.LoginResponse;
import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.utils.SecurityContextUtils;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.exception.CredencialException;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TokenComponent {

    private final JwtEncoder jwtEncoder;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static Long EXPIRES_IN = 3050L;
    private final PedidoService pedidoService;

    public TokenComponent(JwtEncoder jwtEncoder,
                          UsuarioService usuarioService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          PedidoService pedidoService) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.pedidoService = pedidoService;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UsuarioDto user = usuarioService.consultarPorEmail(loginRequest.email())
                .orElseThrow(() -> new RegistroNaoEncontradoException(loginRequest.email(), Usuario.class.getName()));

        validar(user, loginRequest);

        var claims = criarClaims(user);

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, EXPIRES_IN);
    }

    private JwtClaimsSet criarClaims(UsuarioDto usuario) {
        var now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("FOURSALES")
                .subject(SecurityContextUtils.montarSubject(usuario))
                .expiresAt(now.plusSeconds(EXPIRES_IN))
                .issuedAt(now)
                .claim("authorities", usuario.getPerfil())
                .build();
    }

    private void validar(UsuarioDto usuario, LoginRequest loginRequest) {
        if (!bCryptPasswordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
            throw new CredencialException();
        }
    }

    public void cadastrarUsuario(UsuarioDeCriacaoDto usuarioDeCriacaoDto) {
        usuarioService.cadastrarUsuario(usuarioDeCriacaoDto);
    }

    public void deletarUsuario(UUID id) {
        var pedidos = pedidoService.listarPorUsuarioIdPaginado(id, PageRequest.of(0, 1));
        if (pedidos.getTotalElements() > 0) {
            pedidoService.deletar(pedidos.getContent().get(0).getId());
        }
        usuarioService.deletar(id);
    }

    public void atualizarComprasDeUsuarioPorPedidoId(UUID pedidoId, int quantidadeDeItensComprados) {
        PedidoDto pedido = pedidoService.buscaPorId(pedidoId);
        usuarioService.atualizarTotalDeCompra(pedido.getUsuarioId(), quantidadeDeItensComprados);
    }
}
