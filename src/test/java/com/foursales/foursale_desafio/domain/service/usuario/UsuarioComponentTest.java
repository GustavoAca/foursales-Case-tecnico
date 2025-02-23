package com.foursales.foursale_desafio.domain.service.usuario;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.controller.dto.LoginResponse;
import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsuarioComponentTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private UsuarioComponent usuarioComponent;

    @Autowired
    private UsuarioService usuarioService;

    @Nested
    class Dado_um_usuario_existente extends FoursaleDesafioApplicationTests {
        private UsuarioDeCriacaoDto usuarioDto;

        @BeforeEach
        void setup() {
            usuarioDto = mockFactory.construirUsuarioDeCriacao(mockFactory.construirUsuarioDto());
            usuarioService.cadastrarUsuario(usuarioDto);
        }

        @Nested
        class Quando_fazer_login extends FoursaleDesafioApplicationTests {
            private LoginResponse loginComSucesso;

            @BeforeEach
            void setup() {
                loginComSucesso = usuarioComponent.login(mockFactory.construirLoginRequest(usuarioDto.email(), usuarioDto.senha()));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(loginComSucesso);
            }
        }
    }
}