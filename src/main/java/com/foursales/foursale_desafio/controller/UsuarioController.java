package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.controller.dto.LoginRequest;
import com.foursales.foursale_desafio.controller.dto.LoginResponse;
import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.service.usuario.TokenComponent;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final TokenComponent tokenComponent;

    public UsuarioController(TokenComponent tokenComponent) {
        this.tokenComponent = tokenComponent;
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioDeCriacaoDto usuarioDeCriacaoDto) {
        tokenComponent.cadastrarUsuario(usuarioDeCriacaoDto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        tokenComponent.deletarUsuario(id);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return tokenComponent.login(loginRequest);
    }

}
