package com.foursales.foursale_desafio.domain.service.categoria;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaServiceImplTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private CategoriaService categoriaService;

    @Nested
    class Dado_uma_nova_categoria extends FoursaleDesafioApplicationTests {
        private CategoriaDto categoriaDto;

        @BeforeEach
        void setup() {
            categoriaDto = mockFactory.construirCategoriaDto();
        }

        @Nested
        class Quando_salvar extends FoursaleDesafioApplicationTests {
            private CategoriaDto categoriaCriada;

            @BeforeEach
            void setup() {
                categoriaCriada = categoriaService.criar(categoriaDto);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(categoriaCriada);
                assertEquals(categoriaCriada.getNome(), categoriaDto.getNome());
                assertEquals(categoriaCriada.getDescricao(), categoriaDto.getDescricao());
            }
        }
    }

    @Nested
    class Dado_uma_categoria_existente extends FoursaleDesafioApplicationTests {
        private CategoriaDto categoriaCriada;

        @BeforeEach
        void setup() {
            categoriaCriada = mockFactory.construirCategoriaDto();
            categoriaCriada.setId(null);
            categoriaCriada = categoriaService.criar(categoriaCriada);
        }

        @Nested
        class Quando_atualizar extends FoursaleDesafioApplicationTests {
            private CategoriaDto categoriaAtualizada;

            @BeforeEach
            void setup() {
                categoriaAtualizada = categoriaService.atualizar(categoriaCriada.getId(), categoriaCriada);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(categoriaAtualizada);
                assertEquals(categoriaCriada.getNome(), categoriaAtualizada.getNome());
                assertEquals(categoriaCriada.getDescricao(), categoriaAtualizada.getDescricao());
                assertEquals(categoriaCriada.getDataDeCriacao().getHour(), categoriaAtualizada.getDataDeCriacao().getHour());
                assertEquals(categoriaCriada.getDataDeCriacao().getMinute(), categoriaAtualizada.getDataDeCriacao().getMinute());
                assertNotEquals(categoriaCriada.getDataDeModificacao(), categoriaAtualizada.getDataDeModificacao());
            }
        }

        @Nested
        class Quando_listar_categorias extends FoursaleDesafioApplicationTests {
            ResponsePage<CategoriaDto> categoriasDto;

            @BeforeEach
            void setup() {
                categoriasDto = categoriaService.listarPaginado(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, categoriasDto.getTotalElements());
            }
        }

        @Nested
        class Quando_buscar_por_categoria_especifico extends FoursaleDesafioApplicationTests {
            CategoriaDto categoriaDto;

            @BeforeEach
            void setup() {
                categoriaDto = categoriaService.buscaPorId(categoriaCriada.getId());
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertEquals(categoriaCriada.getId(), categoriaDto.getId());
            }
        }

        @Nested
        class Quando_deletar extends FoursaleDesafioApplicationTests {
            private Boolean existCategoria;

            @BeforeEach
            void setup() {
                categoriaService.deletar(categoriaCriada.getId());
                existCategoria = categoriaService.exist(categoriaCriada.getId());
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertFalse(existCategoria);
            }
        }
    }
}