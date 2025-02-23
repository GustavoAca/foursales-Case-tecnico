package com.foursales.foursale_desafio.domain.mapper.usuario;

import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioDto usuarioDto) {
        return Usuario.builder()
                .id(usuarioDto.getId())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .perfil(usuarioDto.getPerfil())
                .nome(usuarioDto.getNome())
                .totalDeComprasRealizadas(usuarioDto.getTotalDeComprasRealizadas())
                .criadoPor(usuarioDto.getCriadoPor())
                .dataDeCriacao(usuarioDto.getDataDeCriacao())
                .modificadoPor(usuarioDto.getModificadoPor())
                .dataDeModificacao(usuarioDto.getDataDeModificacao())
                .build();
    }

    public UsuarioDto toDto(Usuario usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .perfil(usuario.getPerfil())
                .nome(usuario.getNome())
                .totalDeComprasRealizadas(usuario.getTotalDeComprasRealizadas())
                .criadoPor(usuario.getCriadoPor())
                .dataDeCriacao(usuario.getDataDeCriacao())
                .modificadoPor(usuario.getModificadoPor())
                .dataDeModificacao(usuario.getDataDeModificacao())
                .build();
    }
}
