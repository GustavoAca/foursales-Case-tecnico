package com.foursales.foursale_desafio.domain.mapper.categoria;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Categoria categoria;

        @BeforeEach
        void setup() {
            categoria = mockFactory.construirCategoria();
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private CategoriaDto categoriaDto;

            @BeforeEach
            void setup() {
                categoriaDto = categoriaMapper.toDto(categoria);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(categoriaDto.getId());
                assertNotNull(categoriaDto.getDescricao());
                assertNotNull(categoriaDto.getNome());
                assertEquals(categoria.getId(), categoriaDto.getId());
                assertEquals(categoria.getDescricao(), categoriaDto.getDescricao());
                assertEquals(categoria.getNome(), categoriaDto.getNome());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private CategoriaDto categoriaDto;

        @BeforeEach
        void setup() {
            categoriaDto = mockFactory.construirCategoriaDto();
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Categoria categoria;

            @BeforeEach
            void setup() {
                categoria = categoriaMapper.toEntity(categoriaDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(categoria.getId());
                assertNotNull(categoria.getDescricao());
                assertNotNull(categoria.getNome());
                assertEquals(categoria.getId(), categoriaDto.getId());
                assertEquals(categoria.getDescricao(), categoriaDto.getDescricao());
                assertEquals(categoria.getNome(), categoriaDto.getNome());
            }
        }
    }
}