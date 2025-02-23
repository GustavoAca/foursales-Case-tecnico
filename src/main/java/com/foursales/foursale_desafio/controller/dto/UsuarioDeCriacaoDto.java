package com.foursales.foursale_desafio.controller.dto;

import com.foursales.foursale_desafio.domain.core.security.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UsuarioDeCriacaoDto(@Valid @NotNull String email,
                                  @Valid @NotNull Perfil perfil,
                                  @Valid @NotNull String senha,
                                  @Valid @NotNull String nome) {
}
