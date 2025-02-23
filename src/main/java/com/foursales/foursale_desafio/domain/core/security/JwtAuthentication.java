package com.foursales.foursale_desafio.domain.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtAuthentication {

    public static JwtAuthenticationConverter converter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            String scope = jwt.getClaimAsString("authorities");

            if (scope != null) {
                authorities.addAll(Perfil.getHierarquia(scope));
            }
            return authorities;
        });
        return jwtAuthenticationConverter;
    }
}
