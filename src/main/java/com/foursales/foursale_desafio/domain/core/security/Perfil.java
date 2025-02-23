package com.foursales.foursale_desafio.domain.core.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Perfil {
    ROLE_ADMIN,
    ROLE_USER;

    Perfil() {
    }

    public static List<SimpleGrantedAuthority> getHierarquia(String privilegioUsuario) {
        return Arrays.stream(values())
                .map(Enum::name)
                .dropWhile(privilegio -> !privilegio.equals(privilegioUsuario))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
