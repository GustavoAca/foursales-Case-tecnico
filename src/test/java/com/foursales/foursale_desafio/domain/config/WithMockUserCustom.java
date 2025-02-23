package com.foursales.foursale_desafio.domain.config;

import com.foursales.foursale_desafio.domain.core.security.Perfil;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomSecurityContextFactory.class)
public @interface WithMockUserCustom {

    String email();

    Perfil perfil();
}
