package com.foursales.foursale_desafio.domain.mapper.produto;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoPedidoMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private ProdutoPedidoMapper produtoPedidoMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private ProdutoPedido produtoPedido;

        @BeforeEach
        void setup() {
            produtoPedido = mockFactory.construirProdutoPedido(null);
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private ProdutoPedidoDto produtoPedidoDto;

            @BeforeEach
            void setup() {
                produtoPedidoDto = produtoPedidoMapper.toDto(produtoPedido);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(produtoPedidoDto.getId());
                assertNotNull(produtoPedidoDto.getPreco());
                assertNotNull(produtoPedidoDto.getQuantidade());
                assertEquals(produtoPedido.getId(), produtoPedidoDto.getId());
                assertEquals(produtoPedido.getPreco(), produtoPedidoDto.getPreco());
                assertEquals(produtoPedido.getQuantidade(), produtoPedidoDto.getQuantidade());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private ProdutoPedidoDto produtoPedidoDto;

        @BeforeEach
        void setup() {
            produtoPedidoDto = mockFactory.construirProdutoPedidoDto(null);
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private ProdutoPedido produtoPedido;

            @BeforeEach
            void setup() {
                produtoPedido = produtoPedidoMapper.toEntity(produtoPedidoDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(produtoPedido.getId());
                assertNotNull(produtoPedido.getPreco());
                assertNotNull(produtoPedido.getQuantidade());
                assertEquals(produtoPedidoDto.getId(), produtoPedido.getId());
                assertEquals(produtoPedidoDto.getPreco(), produtoPedido.getPreco());
                assertEquals(produtoPedidoDto.getQuantidade(), produtoPedido.getQuantidade());
            }
        }
    }
}