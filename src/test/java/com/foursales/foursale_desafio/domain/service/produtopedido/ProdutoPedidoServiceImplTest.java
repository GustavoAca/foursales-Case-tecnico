package com.foursales.foursale_desafio.domain.service.produtopedido;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoPedidoMapper;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoPedidoServiceImplTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoPedidoService produtoPedidoService;

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private ProdutoPedidoMapper produtoPedidoMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Nested
    class Dado_um_novo_produto_pedido extends FoursaleDesafioApplicationTests {
        private ProdutoPedido produtoPedido;

        @BeforeEach
        void setup() {
            produtoPedido = criarDependenciasParaCriarProdutoPedido();
        }

        @Nested
        class Quando_salvar extends FoursaleDesafioApplicationTests {
            private ProdutoPedidoDto produtoPedidoDto;

            @BeforeEach
            void setup() {
                produtoPedidoDto = produtoPedidoService.criar(produtoPedidoMapper.toDto(produtoPedido));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(produtoPedidoDto);
                assertEquals(produtoPedidoDto.getPedido().getId(), produtoPedido.getPedido().getId());
                assertEquals(produtoPedidoDto.getProduto().getId(), produtoPedido.getProduto().getId());
            }
        }
    }

    private ProdutoPedido criarDependenciasParaCriarProdutoPedido() {
        ProdutoPedido produtoPedido = mockFactory.construirProdutoPedido(null);
        produtoPedido.setId(null);
        produtoPedido.getProduto().setId(null);
        produtoPedido.getPedido().setId(null);
        produtoPedido.getPedido().getUsuario().setId(null);
        produtoPedido.getPedido().setUsuario(usuarioService.salvar(produtoPedido.getPedido().getUsuario()));
        produtoPedido.setPedido(pedidoService.salvar(produtoPedido.getPedido()));
        produtoPedido.getProduto().getSubcategoria().setCategoria(categoriaService.salvar(produtoPedido.getProduto().getSubcategoria().getCategoria()));
        produtoPedido.getProduto().setSubcategoria(subcategoriaService.salvar(produtoPedido.getProduto().getSubcategoria()));
        produtoPedido.setProduto(produtoService.salvar(produtoPedido.getProduto()));
        return produtoPedido;
    }

    @Nested
    class Dado_um_produto_existente extends FoursaleDesafioApplicationTests {
        private ProdutoPedidoDto produtoPedidoCriado;

        @BeforeEach
        void setup() {
            ProdutoPedido produtoPedido = criarDependenciasParaCriarProdutoPedido();
            produtoPedidoCriado = produtoPedidoService.criar(produtoPedidoMapper.toDto(produtoPedido));
        }

        @Nested
        class Quando_atualizar extends FoursaleDesafioApplicationTests {
            private ProdutoPedidoDto produtoPedidoAtualizado;

            @BeforeEach
            void setup() {
                produtoPedidoAtualizado = produtoPedidoService.atualizar(produtoPedidoCriado.getId(), produtoPedidoCriado);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(produtoPedidoAtualizado);
                assertEquals(produtoPedidoCriado.getDataDeCriacao().getHour(), produtoPedidoAtualizado.getDataDeCriacao().getHour());
                assertEquals(produtoPedidoCriado.getDataDeCriacao().getMinute(), produtoPedidoAtualizado.getDataDeCriacao().getMinute());
                assertNotEquals(produtoPedidoCriado.getDataDeModificacao(), produtoPedidoAtualizado.getDataDeModificacao());
            }
        }

        @Nested
        class Quando_listar_produtos_pedido extends FoursaleDesafioApplicationTests {
            private ResponsePage<ProdutoPedidoDto> produtoPedidoDtos;

            @BeforeEach
            void setup() {
                produtoPedidoDtos = produtoPedidoService.listarPaginado(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, produtoPedidoDtos.getTotalElements());
            }
        }

        @Nested
        class Quando_buscar_por_produto_pedido_especifico extends FoursaleDesafioApplicationTests {
            private ProdutoPedidoDto produtoPedidoEncontrado;

            @BeforeEach
            void setup() {
                produtoPedidoEncontrado = produtoPedidoService.buscaPorId(produtoPedidoCriado.getId());
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertEquals(produtoPedidoCriado.getId(), produtoPedidoEncontrado.getId());
            }
        }

        @Nested
        class Quando_buscar_por_produto_pedido extends FoursaleDesafioApplicationTests {
            private ResponsePage<ProdutoPedidoDto> produtosPedidosEncontrado;

            @BeforeEach
            void setup() {
                produtosPedidosEncontrado = produtoPedidoService.listarPaginadoPorPedidoId(produtoPedidoCriado.getPedido().getId(), PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_listar_com_sucesso() {
                assertNotEquals(0L, produtosPedidosEncontrado.getTotalElements());
                assertNotEquals(produtoPedidoCriado.getPedido().getId(), produtosPedidosEncontrado.getContent().get(0).getId());
            }
        }

        @Nested
        class Quando_deletar extends FoursaleDesafioApplicationTests {
            private Boolean existProdutoPedido;

            @BeforeEach
            void setup() {
                produtoPedidoService.deletar(produtoPedidoCriado.getId());
                existProdutoPedido = produtoService.exist(produtoPedidoCriado.getId());
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertFalse(existProdutoPedido);
            }
        }
    }
}