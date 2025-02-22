package com.foursales.foursale_desafio.domain.service.produto;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceImplTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Nested
    class Dado_um_novo_produto extends FoursaleDesafioApplicationTests {
        private Produto produto;

        @BeforeEach
        void setup() {
            produto = mockFactory.construirProduto();
            produto.setId(null);
            produto.getSubcategoria().setCategoria(categoriaService.salvar(produto.getSubcategoria().getCategoria()));
            produto.setSubcategoria(subcategoriaService.salvar(produto.getSubcategoria()));
        }

        @Nested
        class Quando_salvar extends FoursaleDesafioApplicationTests {
            private ProdutoDto produtoCriado;

            @BeforeEach
            void setup() {
                produtoCriado = produtoService.criar(produtoMapper.toDto(produto));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(produtoCriado);
                assertEquals(produtoCriado.getNome(), produto.getNome());
                assertEquals(produtoCriado.getDescricao(), produto.getDescricao());
                assertEquals(produtoCriado.getPreco(), produto.getPreco());
                assertEquals(produtoCriado.getSubcategoriaDto().getId(), produto.getSubcategoria().getId());
            }
        }
    }

    @Nested
    class Dado_um_produto_existente extends FoursaleDesafioApplicationTests {
        private ProdutoDto produtoCriado;

        @BeforeEach
        void setup() {
            Produto produto = mockFactory.construirProduto();
            produto.setId(null);
            produto.getSubcategoria().setCategoria(categoriaService.salvar(produto.getSubcategoria().getCategoria()));
            produto.setSubcategoria(subcategoriaService.salvar(produto.getSubcategoria()));
            produtoCriado = produtoService.criar(produtoMapper.toDto(produto));
        }

        @Nested
        class Quando_atualizar extends FoursaleDesafioApplicationTests {
            private ProdutoDto produtoAtualizado;

            @BeforeEach
            void setup() {
                produtoAtualizado = produtoService.atualizar(produtoCriado.getId(), produtoCriado);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(produtoAtualizado);
                assertEquals(produtoCriado.getNome(), produtoAtualizado.getNome());
                assertEquals(produtoCriado.getDescricao(), produtoAtualizado.getDescricao());
                assertEquals(produtoCriado.getPreco(), produtoAtualizado.getPreco());
                assertEquals(produtoCriado.getSubcategoriaDto().getId(), produtoAtualizado.getSubcategoriaDto().getId());
                assertEquals(produtoCriado.getDataDeCriacao().getHour(), produtoAtualizado.getDataDeCriacao().getHour());
                assertEquals(produtoCriado.getDataDeCriacao().getMinute(), produtoAtualizado.getDataDeCriacao().getMinute());
                assertNotEquals(produtoCriado.getDataDeModificacao(), produtoAtualizado.getDataDeModificacao());
            }
        }

        @Nested
        class Quando_listar_produtos extends FoursaleDesafioApplicationTests {
            ResponsePage<ProdutoDto> produtos;

            @BeforeEach
            void setup() {
                produtos = produtoService.listarPaginado(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, produtos.getTotalElements());
            }
        }

        @Nested
        class Quando_buscar_por_produto_especifico extends FoursaleDesafioApplicationTests {
            private ProdutoDto produtoEncontrado;

            @BeforeEach
            void setup() {
                produtoEncontrado = produtoService.buscaPorId(produtoCriado.getId());
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertEquals(produtoCriado.getId(), produtoEncontrado.getId());
            }
        }

        @Nested
        class Quando_deletar extends FoursaleDesafioApplicationTests {
            private Boolean existProduto;

            @BeforeEach
            void setup() {
                produtoService.deletar(produtoCriado.getId());
                existProduto = produtoService.exist(produtoCriado.getId());
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertFalse(existProduto);
            }
        }
    }
}