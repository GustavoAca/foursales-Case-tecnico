package com.foursales.foursale_desafio.domain.config;

import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.security.Perfil;
import com.foursales.foursale_desafio.domain.core.utils.SecurityContextUtils;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomSecurityContextFactory implements WithSecurityContextFactory<WithMockUserCustom> {

    private final UsuarioService usuarioService;

    public WithMockCustomSecurityContextFactory(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public SecurityContext createSecurityContext(WithMockUserCustom annotation) {
        String email = annotation.email();
        var usuario = usuarioService.consultarPorEmail(email);

        if (usuario.isEmpty()) {
            usuarioService.cadastrarUsuario(new UsuarioDeCriacaoDto(email, annotation.perfil(), "1234", "Gustavo"));
        }

        //todo
        usuario = usuarioService.consultarPorEmail(email);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(SecurityContextUtils.montarSubject(usuario.get()), null, Perfil.getHierarquia(usuario.get().getPerfil().name()));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        return SecurityContextHolder.getContext();
    }
}
