package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UsuarioService extends BaseService<Usuario, UUID> {
    void cadastrarUsuario(UsuarioDeCriacaoDto usuarioDeCriacaoDto);

    ResponsePage<Usuario> getMaioresCompradores(Pageable pageable);
}
