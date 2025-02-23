package com.foursales.foursale_desafio.domain.core.utils;

import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityContextUtils {

    private static final String USUARIO_ID = "usuarioId";
    private static final String EMAIL = "email";

    private SecurityContextUtils() {
    }

    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(auth) ? extractUsername(auth.getName()) : null;
    }

    private static String extractUsername(String input) {
        Pattern pattern = Pattern.compile(String.format("%s:\\s*([^,\\s]+)", EMAIL));
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    public static UUID getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(auth) ? extractId(auth.getName()) : null;
    }

    private static UUID extractId(String input) {
        Pattern pattern = Pattern.compile(String.format("%s:\\s*([a-fA-F0-9-]+)", USUARIO_ID));
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? UUID.fromString(matcher.group(1).trim()) : null;
    }

    public static String montarSubject(UsuarioDto usuario) {
        return String.format("%s: %s, %s: %s",
                USUARIO_ID, usuario.getId(),
                EMAIL, usuario.getEmail());
    }
}
