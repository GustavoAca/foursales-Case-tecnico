package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.controller.dto.LoginRequest;
import com.foursales.foursale_desafio.controller.dto.LoginResponse;
import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.utils.SecurityContextUtils;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.exception.CredencialException;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class UsuarioComponent {

    private final JwtEncoder jwtEncoder;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static Long EXPIRES_IN = 3050L;
    private final PedidoService pedidoService;

    public UsuarioComponent(JwtEncoder jwtEncoder,
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
                .orElseThrow(() -> new RegistroNaoEncontradoException(loginRequest.email(), "Usuario"));

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

    public PedidoDto criarPedido(PedidoDto pedidoDto) {
        return pedidoService.criar(pedidoDto);
    }

    public PedidoDto atualizarPedido(UUID id, PedidoDto pedidoDto) {
        return pedidoService.atualizar(id, pedidoDto);
    }

    public void adicionarProdutoPedido(List<ProdutoPedidoDto> produtoPedidoDtos, UUID pedidoId) {
        pedidoService.adicionarProduto(produtoPedidoDtos, pedidoId);
    }

    public PedidoDto atualizarStatusDePedido(UUID id, Status status) {
        return pedidoService.atualizarStatus(id, status);
    }

    public Boolean isPedidoDisponivelParaPagamento(UUID id) {
        return pedidoService.isDisponivelParaPagamento(id);
    }

    public void removerProdutoPedido(List<UUID> ids) {
        pedidoService.removerProduto(ids);
    }

    public ResponsePage<PedidoDto> listarPedidoPaginado(Pageable pageable) {
        return pedidoService.listarPaginado(pageable);
    }

    public void deletarPedido(UUID id) {
        pedidoService.deletar(id);
    }

    public PedidoDto buscarPedidoPorId(UUID id) {
        return pedidoService.buscaPorId(id);
    }

    public ResponsePage<PedidoDto> listarPedidoPorUsuarioIdPaginado(UUID usuarioId, Pageable pageable) {
        return pedidoService.listarPorUsuarioIdPaginado(usuarioId, pageable);
    }
}
