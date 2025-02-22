package com.foursales.foursale_desafio.domain.service.subcategoria;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.categoria.CategoriaMapper;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class SubcategoriaServiceImplTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Nested
    class Dado_uma_nova_subcategoria extends FoursaleDesafioApplicationTests {
        private SubcategoriaDto subcategoriaDto;

        @BeforeEach
        void setup() {
            subcategoriaDto = mockFactory.construirSubcategoriaDto();
            subcategoriaDto.setCategoria(categoriaService.criar(subcategoriaDto.getCategoria()));
        }

        @Nested
        class Quando_salvar extends FoursaleDesafioApplicationTests {
            private SubcategoriaDto subcategoriaCriada;

            @BeforeEach
            void setup() {
                subcategoriaCriada = subcategoriaService.criar(subcategoriaDto);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(subcategoriaCriada);
                assertEquals(subcategoriaCriada.getNome(), subcategoriaDto.getNome());
                assertEquals(subcategoriaCriada.getDescricao(), subcategoriaDto.getDescricao());
            }
        }
    }

    @Nested
    class Dado_uma_subcategoria_existente extends FoursaleDesafioApplicationTests {
        private SubcategoriaDto subcategoriaCriada;

        @BeforeEach
        void setup() {
            subcategoriaCriada = mockFactory.construirSubcategoriaDto();
            subcategoriaCriada.setId(null);
            subcategoriaCriada.setCategoria(categoriaService.criar(subcategoriaCriada.getCategoria()));
            subcategoriaCriada = subcategoriaService.criar(subcategoriaCriada);
        }

        @Nested
        class Quando_atualizar extends FoursaleDesafioApplicationTests {
            private SubcategoriaDto subcategoriaAtualizada;

            @BeforeEach
            void setup() {
                subcategoriaAtualizada = subcategoriaService.atualizar(subcategoriaCriada.getId(), subcategoriaCriada);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(subcategoriaAtualizada);
                assertEquals(subcategoriaCriada.getNome(), subcategoriaAtualizada.getNome());
                assertEquals(subcategoriaCriada.getDescricao(), subcategoriaAtualizada.getDescricao());
                assertEquals(subcategoriaCriada.getDataDeCriacao().getHour(), subcategoriaAtualizada.getDataDeCriacao().getHour());
                assertEquals(subcategoriaCriada.getDataDeCriacao().getMinute(), subcategoriaAtualizada.getDataDeCriacao().getMinute());
                assertNotEquals(subcategoriaCriada.getDataDeModificacao(), subcategoriaAtualizada.getDataDeModificacao());
            }
        }

        @Nested
        class Quando_listar_subcategorias extends FoursaleDesafioApplicationTests {
            ResponsePage<SubcategoriaDto> subcategoriasDto;

            @BeforeEach
            void setup() {
                subcategoriasDto = subcategoriaService.listarPaginado(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, subcategoriasDto.getTotalElements());
            }
        }

        @Nested
        class Quando_listar_subcategorias_por_categoria extends FoursaleDesafioApplicationTests {
            ResponsePage<SubcategoriaDto> subcategoriasDto;

            @BeforeEach
            void setup() {
                subcategoriasDto = subcategoriaService.listarPorCategoriaId(subcategoriaCriada.getCategoria().getId(), PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, subcategoriasDto.getTotalElements());
                assertEquals(subcategoriaCriada.getCategoria().getId(), subcategoriasDto.getContent().get(0).getCategoria().getId());
            }
        }

        @Nested
        class Quando_buscar_por_sucategoria_especifico extends FoursaleDesafioApplicationTests {
            SubcategoriaDto subcategoriaDto;

            @BeforeEach
            void setup() {
                subcategoriaDto = subcategoriaService.buscaPorId(subcategoriaCriada.getId());
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertEquals(subcategoriaCriada.getId(), subcategoriaDto.getId());
            }
        }

        @Nested
        class Quando_deletar extends FoursaleDesafioApplicationTests {
            private Boolean existSubcategoria;

            @BeforeEach
            void setup() {
                subcategoriaService.deletar(subcategoriaCriada.getId());
                existSubcategoria = subcategoriaService.exist(subcategoriaCriada.getId());
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertFalse(existSubcategoria);
            }
        }
    }
}