package com.foursales.foursale_desafio.domain.mapper.categoria;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SubcategoriaMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private SubcategoriaMapper subcategoriaMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Subcategoria subcategoria;

        @BeforeEach
        void setup() {
            subcategoria = mockFactory.construirSubcategoria();
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private SubcategoriaDto subcategoriaDto;

            @BeforeEach
            void setup() {
                subcategoriaDto = subcategoriaMapper.toDto(subcategoria);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(subcategoriaDto.getId());
                assertNotNull(subcategoriaDto.getDescricao());
                assertNotNull(subcategoriaDto.getNome());
                assertNotNull(subcategoriaDto.getCategoria());
                assertEquals(subcategoria.getId(), subcategoriaDto.getId());
                assertEquals(subcategoria.getDescricao(), subcategoriaDto.getDescricao());
                assertEquals(subcategoria.getNome(), subcategoriaDto.getNome());
                assertEquals(subcategoria.getCategoria().getId(), subcategoriaDto.getCategoria().getId());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private SubcategoriaDto subcategoriaDto;

        @BeforeEach
        void setup() {
            subcategoriaDto = mockFactory.construirSubcategoriaDto();
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Subcategoria subcategoria;

            @BeforeEach
            void setup() {
                subcategoria = subcategoriaMapper.toEntity(subcategoriaDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(subcategoria.getId());
                assertNotNull(subcategoria.getDescricao());
                assertNotNull(subcategoria.getNome());
                assertNotNull(subcategoria.getCategoria());
                assertEquals(subcategoria.getId(), subcategoriaDto.getId());
                assertEquals(subcategoria.getDescricao(), subcategoriaDto.getDescricao());
                assertEquals(subcategoria.getNome(), subcategoriaDto.getNome());
                assertEquals(subcategoria.getCategoria().getId(), subcategoriaDto.getCategoria().getId());
            }
        }
    }
}