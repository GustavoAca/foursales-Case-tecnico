package com.foursales.foursale_desafio.domain.mapper.produto;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private ProdutoMapper pedidprodutoMapperMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Produto produto;

        @BeforeEach
        void setup() {
            produto = mockFactory.construirProduto();
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private ProdutoDto produtoDto;

            @BeforeEach
            void setup() {
                produtoDto = pedidprodutoMapperMapper.toDto(produto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(produtoDto.getId());
                assertNotNull(produtoDto.getNome());
                assertNotNull(produtoDto.getDescricao());
                assertNotNull(produtoDto.getPreco());
                assertNotNull(produtoDto.getSubcategoriaDto());
                assertNotNull(produtoDto.getQuantidadeEmEstoque());
                assertEquals(produto.getId(), produtoDto.getId());
                assertEquals(produto.getNome(), produtoDto.getNome());
                assertEquals(produto.getDescricao(), produtoDto.getDescricao());
                assertEquals(produto.getPreco(), produtoDto.getPreco());
                assertEquals(produto.getSubcategoria().getId(), produtoDto.getSubcategoriaDto().getId());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private ProdutoDto produtoDto;

        @BeforeEach
        void setup() {
            produtoDto = mockFactory.construirProdutoDto();
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Produto produto;

            @BeforeEach
            void setup() {
                produto = pedidprodutoMapperMapper.toEntity(produtoDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(produto.getId());
                assertNotNull(produto.getNome());
                assertNotNull(produto.getDescricao());
                assertNotNull(produto.getPreco());
                assertNotNull(produto.getSubcategoria());
                assertNotNull(produto.getQuantidadeEmEstoque());
                assertEquals(produtoDto.getId(), produto.getId());
                assertEquals(produtoDto.getNome(), produto.getNome());
                assertEquals(produtoDto.getDescricao(), produto.getDescricao());
                assertEquals(produtoDto.getPreco(), produto.getPreco());
                assertEquals(produtoDto.getSubcategoriaDto().getId(), produto.getSubcategoria().getId());
            }
        }
    }
}