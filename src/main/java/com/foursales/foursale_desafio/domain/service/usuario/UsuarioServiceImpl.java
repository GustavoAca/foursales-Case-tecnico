package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.mapper.usuario.UsuarioMapper;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import com.foursales.foursale_desafio.domain.repository.UsuarioRepository;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.exception.RegistroJaCadastradoException;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UUID, UsuarioRepository> implements UsuarioService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsuarioMapper usuarioMapper;

    protected UsuarioServiceImpl(UsuarioRepository repo,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 UsuarioMapper usuarioMapper,
                                 PedidoService pedidoService) {
        super(repo);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public void cadastrarUsuario(UsuarioDeCriacaoDto usuarioDeCriacaoDto) {
        Optional<Usuario> usuario = repo.findByEmail(usuarioDeCriacaoDto.email());

        if (usuario.isPresent()) {
            throw new RegistroJaCadastradoException(String
                    .format("Usuario %s existente", usuarioDeCriacaoDto.email()));
        }

        salvar(Usuario.builder()
                .nome(usuarioDeCriacaoDto.nome())
                .senha(bCryptPasswordEncoder.encode(usuarioDeCriacaoDto.senha()))
                .email(usuarioDeCriacaoDto.email())
                .perfil(usuarioDeCriacaoDto.perfil())
                .build());
    }

    @Override
    public ResponsePage<UsuarioDto> getMaioresCompradores(Pageable pageable) {
        return mapearPage(repo.findAllByOrderByTotalDeComprasRealizadasDesc(pageable), usuarioMapper::toDto);
    }

    @Override
    public void atualizarTotalDeCompra(UUID usuarioId, int comprasRealizadas) {
        Usuario usuario = buscarPorId(usuarioId)
                .orElseThrow(() -> new RegistroNaoEncontradoException(usuarioId, "Usuario"));
        usuario.setTotalDeComprasRealizadas(usuario.getTotalDeComprasRealizadas() + comprasRealizadas);
        salvar(usuario);
    }

    @Override
    public Optional<UsuarioDto> consultarPorEmail(String email) {
        return repo.findByEmail(email).map(usuarioMapper::toDto);
    }
}
