package com.foursales.foursale_desafio.domain.service.analise;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.controller.dto.UsuarioDeCriacaoDto;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.mapper.usuario.UsuarioMapper;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import com.foursales.foursale_desafio.domain.service.pagamento.PagamentoService;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioService;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnaliseServiceImplTest extends FoursaleDesafioApplicationTests {

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

    @Autowired
    private AnaliseService analiseService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Nested
    class Dado_uma_analise_requisitada extends FoursaleDesafioApplicationTests {

        @BeforeEach
        void setup() {
            for (int i = 55; i >= 50; i--) {
                UsuarioDto usuario = mockFactory.construirUsuarioDto();
                usuarioService.cadastrarUsuario(new UsuarioDeCriacaoDto(usuario.getEmail(), usuario.getPerfil(), usuario.getSenha(), usuario.getNome()));
                String email = usuario.getEmail();
                usuario = usuarioService.consultarPorEmail(usuario.getEmail())
                        .orElseThrow(() -> new RegistroNaoEncontradoException(email, "Usuario"));

                usuarioService.atualizarTotalDeCompra(usuario.getId(), 2000 + i);
            }
        }

        @Nested
        class Quando_buscar_os_cinco_maiores_compradores extends FoursaleDesafioApplicationTests {
            private ResponsePage<UsuarioDto> maioresCompradores;

            @BeforeEach
            void setup() {
                maioresCompradores = analiseService.getMaioresCompradores(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertEquals(5L, maioresCompradores.getNumberOfElements());
                assertEquals(2055, maioresCompradores.getContent().get(0).getTotalDeComprasRealizadas());
                assertEquals(2054, maioresCompradores.getContent().get(1).getTotalDeComprasRealizadas());
                assertEquals(2053, maioresCompradores.getContent().get(2).getTotalDeComprasRealizadas());
                assertEquals(2052, maioresCompradores.getContent().get(3).getTotalDeComprasRealizadas());
                assertEquals(2051, maioresCompradores.getContent().get(4).getTotalDeComprasRealizadas());
            }
        }
    }

    @Nested
    class Dado_clientes_com_compras_realizadas extends FoursaleDesafioApplicationTests {

        private UUID usuarioId;

        @BeforeEach
        void setup() {
            for (int i = 55; i >= 50; i--) {
                usuarioId = realizarCompra(i, null);
            }
        }

        @Nested
        class Quando_consultar_o_valor_medio_gasto_por_usuarios extends FoursaleDesafioApplicationTests {
            private ResponsePage<GastoMedioUsuarioProjection> gastoMedioPorUsuarios;

            @BeforeEach
            void setup() {
                gastoMedioPorUsuarios = analiseService.getValorGastoMedioPorUsuario(PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotEquals(0L, gastoMedioPorUsuarios.getNumberOfElements());
                assertNotNull(gastoMedioPorUsuarios.getContent().get(0).getUsuarioId());
                assertNotNull(gastoMedioPorUsuarios.getContent().get(0).getMediaDeGasto());
            }
        }

        @Nested
        class Quando_consultar_o_valor_medio_gasto_por_usuario_especifico extends FoursaleDesafioApplicationTests {
            private GastoMedioUsuarioProjection gastoMedioUsuario;

            @BeforeEach
            void setup() {
                gastoMedioUsuario = analiseService.getValorGastoMedioPorUsuarioId(usuarioId);
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotNull(gastoMedioUsuario);
                assertEquals(usuarioId, gastoMedioUsuario.getUsuarioId());
            }
        }

        @Nested
        class Quando_buscar_faturamento_por_periodo extends FoursaleDesafioApplicationTests {
            private ResponsePage<FaturamentoProjection> faturamentoProjections;

            @BeforeEach
            void setup() {
                faturamentoProjections = analiseService.getFaturamentoPorPeriodo(2, 2025, PageRequest.of(0, 5));
            }

            @Test
            void Entao_deve_ter_sucesso() {
                assertNotEquals(0L, faturamentoProjections.getNumberOfElements());
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

    private UUID realizarCompra(int quantidade, Integer quantidadeNoEstoque) {
        ProdutoDto produtoDto = salvarProduto(Objects.nonNull(quantidadeNoEstoque) ? quantidadeNoEstoque : 10);
        ProdutoPedidoDto produtoPedidoDto = ProdutoPedidoDto.builder()
                .preco(produtoDto.getPreco())
                .quantidade(quantidade)
                .produto(produtoDto)
                .build();
        var usuario = mockFactory.construirUsuario();
        usuario.setId(null);
        PedidoDto pedidoDto = PedidoDto.builder()
                .produtosPedidos(List.of(produtoPedidoDto))
                .usuario(usuarioMapper.toDto(usuarioService.salvar(usuario)))
                .build();
        PedidoDto pedidoCriado = pedidoService.criar(pedidoDto);
        pagamentoService.realizarPagamento(pedidoCriado.getId());
        return pedidoCriado.getUsuarioId();
    }
}