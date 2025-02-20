package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioDeCriacaoDto usuarioDeCriacaoDto) {
        usuarioService.cadastrarUsuario(usuarioDeCriacaoDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deletar(@PathVariable UUID id) {
        return usuarioService.deletar(id);
    }
}
