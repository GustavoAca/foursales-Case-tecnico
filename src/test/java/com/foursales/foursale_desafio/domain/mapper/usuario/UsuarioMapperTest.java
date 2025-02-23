package com.foursales.foursale_desafio.domain.mapper.usuario;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsuarioMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Usuario usuario;

        @BeforeEach
        void setup() {
            usuario = mockFactory.construirUsuario();
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private UsuarioDto usuarioDto;

            @BeforeEach
            void setup() {
                usuarioDto = usuarioMapper.toDto(usuario);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(usuarioDto.getId());
                assertNotNull(usuarioDto.getEmail());
                assertNotNull(usuarioDto.getSenha());
                assertNotNull(usuarioDto.getPerfil());
                assertNotNull(usuarioDto.getNome());
                assertNotNull(usuarioDto.getTotalDeComprasRealizadas());
                assertEquals(usuario.getId(), usuarioDto.getId());
                assertEquals(usuario.getEmail(), usuarioDto.getEmail());
                assertEquals(usuario.getSenha(), usuarioDto.getSenha());
                assertEquals(usuario.getPerfil(), usuarioDto.getPerfil());
                assertEquals(usuario.getNome(), usuarioDto.getNome());
                assertEquals(usuario.getTotalDeComprasRealizadas(), usuarioDto.getTotalDeComprasRealizadas());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private UsuarioDto usuarioDto;

        @BeforeEach
        void setup() {
            usuarioDto = mockFactory.construirUsuarioDto();
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Usuario usuario;

            @BeforeEach
            void setup() {
                usuario = usuarioMapper.toEntity(usuarioDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(usuario.getId());
                assertNotNull(usuario.getEmail());
                assertNotNull(usuario.getSenha());
                assertNotNull(usuario.getPerfil());
                assertNotNull(usuario.getNome());
                assertNotNull(usuario.getTotalDeComprasRealizadas());
                assertEquals(usuarioDto.getId(), usuario.getId());
                assertEquals(usuarioDto.getEmail(), usuario.getEmail());
                assertEquals(usuarioDto.getSenha(), usuario.getSenha());
                assertEquals(usuarioDto.getPerfil(), usuario.getPerfil());
                assertEquals(usuarioDto.getNome(), usuario.getNome());
                assertEquals(usuarioDto.getTotalDeComprasRealizadas(), usuario.getTotalDeComprasRealizadas());
            }
        }
    }
}