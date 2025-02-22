package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.repository.UsuarioRepository;
import com.foursales.foursale_desafio.exception.RegistroJaCadastradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UUID, UsuarioRepository> implements UsuarioService {

    protected UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
    }

    @Override
    public void cadastrarUsuario(UsuarioDeCriacaoDto usuarioDeCriacaoDto) {
        Optional<Usuario> usuario = repo.findByEmail(usuarioDeCriacaoDto.email());

        if(usuario.isPresent()){
            throw new RegistroJaCadastradoException(String
                    .format("Usuario %s existente", usuarioDeCriacaoDto.email()));
        }

        salvar(Usuario.builder()
                .nome(usuarioDeCriacaoDto.nome())
                .senha(usuarioDeCriacaoDto.senha())
                .email(usuarioDeCriacaoDto.email())
                .perfil(usuarioDeCriacaoDto.perfil())
                .build());
    }

    @Override
    public ResponsePage<Usuario> getMaioresCompradores(Pageable pageable) {
        return repo.findAllByOrderByTotalDeComprasRealizadasDesc(pageable);
    }
}
