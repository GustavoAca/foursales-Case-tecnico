package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoServiceImplTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private PagamentoService pagamentoService;
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

    @Nested
    class Dado_um_pedido extends FoursaleDesafioApplicationTests {
        private PedidoDto pedidoDto;
        private PedidoDto pedidoCriado;
        private boolean isPagamentoRealizado;

        @Nested
        class Quando_puder_realizar_pagamento extends FoursaleDesafioApplicationTests {

            private ProdutoDto produtoAtualizado;
            private static final Integer QUANTIDADE_DE_ESTOQUE_RESTANTE = 0;

            @BeforeEach
            void setup() {
                ProdutoDto produtoDto = salvarProduto(1);
                ProdutoPedidoDto produtoPedidoDto = ProdutoPedidoDto.builder()
                        .preco(produtoDto.getPreco())
                        .quantidade(1)
                        .produto(produtoDto)
                        .build();

                pedidoDto = PedidoDto.builder()
                        .produtosPedidos(List.of(produtoPedidoDto))
                        .usuario(usuarioService.salvar(mockFactory.construirUsuario()))
                        .build();
                pedidoCriado = pedidoService.criar(pedidoDto);
                isPagamentoRealizado = pagamentoService.realizarPagamento(pedidoCriado.getId());
                produtoAtualizado = produtoService.buscaPorId(produtoDto.getId());
            }

            @Test
            void Entao_deve_ser_pago_com_sucesso() {
                assertTrue(isPagamentoRealizado);
                assertEquals(QUANTIDADE_DE_ESTOQUE_RESTANTE, produtoAtualizado.getQuantidadeEmEstoque());
            }
        }

        @Nested
        class Quando_nao_puder_realizar_pagamento extends FoursaleDesafioApplicationTests {

            @Nested
            class Quando_pedido_for_cancelado_automaticamente extends FoursaleDesafioApplicationTests {
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
                            .usuario(usuarioService.salvar(mockFactory.construirUsuario()))
                            .build();
                    pedidoCriado = pedidoService.criar(pedidoDto);
                    isPagamentoRealizado = pagamentoService.realizarPagamento(pedidoCriado.getId());
                }

                @Test
                void Entao_nao_deve_pagar() {
                    assertEquals(Status.CANCELADO_AUTOMATICAMENTE, pedidoCriado.getStatus());
                    assertFalse(isPagamentoRealizado);
                }
            }

            @Nested
            class Quando_pedido_ja_tiver_sido_pago extends FoursaleDesafioApplicationTests {
                private static final Integer QUANTIDADE_DE_ESTOQUE_RESTANTE = 10;
                private ProdutoDto produtoAtualizado;

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
                            .usuario(usuarioService.salvar(mockFactory.construirUsuario()))
                            .status(Status.CONFIRMADO)
                            .build();
                    pedidoCriado = pedidoService.criar(pedidoDto);
                    isPagamentoRealizado = pagamentoService.realizarPagamento(pedidoCriado.getId());
                    produtoAtualizado = produtoService.buscaPorId(produtoDto.getId());
                }

                @Test
                void Entao_nao_deve_pagar() {
                    assertEquals(Status.CONFIRMADO, pedidoCriado.getStatus());
                    assertFalse(isPagamentoRealizado);
                    assertEquals(QUANTIDADE_DE_ESTOQUE_RESTANTE, produtoAtualizado.getQuantidadeEmEstoque());
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
}