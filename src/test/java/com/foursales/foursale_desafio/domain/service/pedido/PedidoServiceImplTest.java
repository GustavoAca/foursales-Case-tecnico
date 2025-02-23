package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.config.WithMockUserCustom;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.security.Perfil;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoPedidoMapper;
import com.foursales.foursale_desafio.domain.mapper.usuario.UsuarioMapper;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import com.foursales.foursale_desafio.exception.ProdutoSemEstoqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceImplTest extends FoursaleDesafioApplicationTests {

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

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoPedidoService produtoPedidoService;

    @Autowired
    private ProdutoPedidoMapper produtoPedidoMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Nested
    class Dado_um_pedido extends FoursaleDesafioApplicationTests {
        private PedidoDto pedidoDto;
        private PedidoDto pedidoCriado;

        @Nested
        class Quando_criar_com_todos_produtos_desejados_em_estoque extends FoursaleDesafioApplicationTests {
            private ResponsePage<ProdutoPedidoDto> produtosPedidos;

            @BeforeEach
            void setup() {
                ProdutoDto produtoDto = salvarProduto(null);
                ProdutoPedidoDto produtoPedidoDto = ProdutoPedidoDto.builder()
                        .preco(produtoDto.getPreco())
                        .quantidade(1)
                        .produto(produtoDto)
                        .build();

                pedidoDto = PedidoDto.builder()
                        .produtosPedidos(List.of(produtoPedidoDto))
                        .usuario(usuarioMapper.toDto(usuarioService.salvar(mockFactory.construirUsuario())))
                        .build();
                pedidoCriado = pedidoService.criar(pedidoDto);
                produtosPedidos = produtoPedidoService.listarPaginadoPorPedidoId(pedidoCriado.getId(), PageRequest.of(0, 1));
            }

            @Test
            void Entao_deve_ser_criado_com_sucesso() {
                assertNotNull(pedidoCriado);
                assertEquals(1L, produtosPedidos.getTotalElements());
            }
        }

        @Nested
        class Quando_criar_e_algum_produto_desejado_nao_tiver_estoque extends FoursaleDesafioApplicationTests {

            @BeforeEach
            void setup() {
                ProdutoDto produtoDto = salvarProduto(0);
                ProdutoPedidoDto produtoPedidoDto = ProdutoPedidoDto.builder()
                        .preco(produtoDto.getPreco())
                        .quantidade(1)
                        .produto(produtoDto)
                        .build();

                pedidoDto = PedidoDto.builder()
                        .produtosPedidos(List.of(produtoPedidoDto))
                        .usuario(usuarioMapper.toDto(usuarioService.salvar(mockFactory.construirUsuario())))
                        .build();
                pedidoCriado = pedidoService.criar(pedidoDto);
            }

            @Test
            void Entao_deve_criar_mas_retornar_que_pedido_foi_cancelado_automaticamente() {
                assertNotNull(pedidoCriado);
                assertEquals(Status.CANCELADO_AUTOMATICAMENTE, pedidoCriado.getStatus());
            }
        }

        @Nested
        class Quando_existir_pedido extends FoursaleDesafioApplicationTests {

            private ResponsePage<ProdutoPedidoDto> produtosPedidosPaginado;
            private final List<ProdutoPedido> produtosPedidos = new LinkedList<>();

            @BeforeEach
            void setup() {
                ProdutoDto produtoDto = salvarProduto(null);
                ProdutoPedidoDto produtoPedidoDto = ProdutoPedidoDto.builder()
                        .preco(produtoDto.getPreco())
                        .quantidade(1)
                        .produto(produtoDto)
                        .build();

                pedidoDto = PedidoDto.builder()
                        .produtosPedidos(List.of(produtoPedidoDto))
                        .usuario(usuarioMapper.toDto(usuarioService.salvar(mockFactory.construirUsuario())))
                        .build();
                pedidoCriado = pedidoService.criar(pedidoDto);
            }

            @Nested
            class Quando_verificar_se_pedido_pode_ser_pago extends FoursaleDesafioApplicationTests {
                private Boolean isDisponivelParaPagamento;

                @BeforeEach
                void setup() {
                    isDisponivelParaPagamento = pedidoService.isDisponivelParaPagamento(pedidoCriado.getId());
                }

                @Test
                void Entao_deve_ter_sucesso() {
                    assertTrue(isDisponivelParaPagamento);
                }
            }

            @Nested
            class Quando_listar_por_usuario extends FoursaleDesafioApplicationTests {
                private ResponsePage<PedidoDto> pedidosPorUsuario;

                @BeforeEach
                void setup() {
                    pedidosPorUsuario = pedidoService.listarPorUsuarioIdPaginado(pedidoCriado.getUsuarioId(), PageRequest.of(0, 2));
                }

                @Test

                @WithMockUserCustom(email = "galasdalas51@gmail.com", perfil = Perfil.ROLE_ADMIN)
                void Entao_deve_listar_com_sucesso() {
                    assertEquals(1L, pedidosPorUsuario.getTotalElements());
                    assertEquals(pedidoCriado.getUsuarioId(), pedidosPorUsuario.getContent().get(0).getUsuarioId());
                }
            }

            @Nested
            class Quando_listar_todos_pedidos extends FoursaleDesafioApplicationTests {
                private ResponsePage<PedidoDto> pedidos;

                @BeforeEach
                void setup() {
                    pedidos = pedidoService.listarPaginado(PageRequest.of(0, 2));
                }

                @Test
                void Entao_deve_listar_com_sucesso() {
                    assertNotEquals(0L, pedidos.getTotalElements());
                }
            }

            @Nested
            class Quando_adicionar_produtos extends FoursaleDesafioApplicationTests {

                @BeforeEach
                void setup() {
                    salvarProdutosPedidos(produtosPedidos, 2);
                    pedidoService.adicionarProduto(produtosPedidos.stream().map(produtoPedidoMapper::toDto).toList(), pedidoCriado.getId());
                    produtosPedidosPaginado = produtoPedidoService.listarPaginadoPorPedidoId(pedidoCriado.getId(), PageRequest.of(0, 5));
                    pedidoCriado = pedidoService.buscaPorId(pedidoCriado.getId());
                }

                @Test
                void Entao_deve_ser_adicionado_com_sucesso() {
                    assertEquals(4L, produtosPedidosPaginado.getTotalElements());
                    assertEquals(new BigDecimal("182.00"), pedidoCriado.getValorTotal());
                }
            }

            @Nested
            class Quando_adicionar_produtos_sem_estoque extends FoursaleDesafioApplicationTests {

                @BeforeEach
                void setup() {
                    salvarProdutosPedidos(produtosPedidos, 0);
                }

                @Test
                void Entao_deve_ser_adicionado_com_sucesso_mas_retornando_que_pedido_foi_cancelado() {
                    assertThrows(ProdutoSemEstoqueException.class,
                            () -> pedidoService.adicionarProduto(produtosPedidos.stream().map(produtoPedidoMapper::toDto).toList(), pedidoCriado.getId()));
                }
            }

            @Nested
            class Quando_remover_produtos extends FoursaleDesafioApplicationTests {

                @BeforeEach
                void setup() {
                    salvarProdutosPedidos(produtosPedidos, 2);
                    pedidoService.adicionarProduto(produtosPedidos.stream().map(produtoPedidoMapper::toDto).toList(), pedidoCriado.getId());
                    produtosPedidosPaginado = produtoPedidoService.listarPaginadoPorPedidoId(pedidoCriado.getId(), PageRequest.of(0, 2));
                }

                @Test
                void Entao_deve_ser_adicionado_com_sucesso() {
                    assertDoesNotThrow(() -> pedidoService.removerProduto(produtosPedidosPaginado.getContent().stream().map(ProdutoPedidoDto::getId).toList()));
                }
            }
        }
    }

    private ProdutoDto salvarProduto(Integer quantidadeNoEstoque) {
        Produto produto = mockFactory.construirProduto();
        produto.setId(null);
        produto.getSubcategoria().getCategoria().setId(null);
        produto.getSubcategoria().setCategoria(categoriaService.salvar(produto.getSubcategoria().getCategoria()));
        produto.getSubcategoria().setId(null);
        produto.setSubcategoria(subcategoriaService.salvar(produto.getSubcategoria()));

        if (Objects.nonNull(quantidadeNoEstoque)) {
            produto.setQuantidadeEmEstoque(quantidadeNoEstoque);
        }

        return produtoService.criar(produtoMapper.toDto(produto));
    }

    private void salvarProdutosPedidos(List<ProdutoPedido> produtosPedidos, int quantidadeNoEstoque) {
        for (int i = 0; i < 3; i++) {
            var produto = salvarProduto(quantidadeNoEstoque);
            produtosPedidos.add(produtoPedidoMapper.toEntity(ProdutoPedidoDto.builder()
                    .produto(produto)
                    .quantidade(2)
                    .preco(produto.getPreco())
                    .build()));
        }
    }
}