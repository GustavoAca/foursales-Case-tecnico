package com.foursales.foursale_desafio.domain.mapper.pedido;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMapperTest extends FoursaleDesafioApplicationTests {

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Pedido pedido;

        @BeforeEach
        void setup() {
            pedido = mockFactory.construirPedido(null);
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private PedidoDto pedidoDto;

            @BeforeEach
            void setup() {
                pedidoDto = pedidoMapper.toDto(pedido);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(pedidoDto.getId());
                assertNotNull(pedidoDto.getProdutos());
                assertNotNull(pedidoDto.getUsuario());
                assertNotNull(pedidoDto.getStatus());
                assertNotNull(pedidoDto.getValorTotal());
                assertEquals(pedido.getId(), pedidoDto.getId());
                assertEquals(pedido.getProdutos().size(), pedidoDto.getProdutos().size());
                assertEquals(pedido.getUsuario().getId(), pedidoDto.getUsuario().getId());
                assertEquals(pedido.getStatus(), pedidoDto.getStatus());
                assertEquals(pedido.getValorTotal(), pedidoDto.getValorTotal());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private PedidoDto pedidoDto;

        @BeforeEach
        void setup() {
            pedidoDto = mockFactory.construirPedidoDto(null);
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Pedido pedido;

            @BeforeEach
            void setup() {
                pedido = pedidoMapper.toEntity(pedidoDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(pedido.getId());
                assertNotNull(pedido.getProdutos());
                assertNotNull(pedido.getUsuario());
                assertNotNull(pedido.getStatus());
                assertNotNull(pedido.getValorTotal());
                assertEquals(pedidoDto.getId(), pedido.getId());
                assertEquals(pedidoDto.getProdutos().size(), pedido.getProdutos().size());
                assertEquals(pedidoDto.getUsuario().getId(), pedido.getUsuario().getId());
                assertEquals(pedidoDto.getStatus(), pedido.getStatus());
                assertEquals(pedidoDto.getValorTotal(), pedido.getValorTotal());
            }
        }
    }
}