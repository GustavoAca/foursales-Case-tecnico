package com.foursales.foursale_desafio.domain.mapper.pagamento;

import com.foursales.foursale_desafio.FoursaleDesafioApplicationTests;
import com.foursales.foursale_desafio.domain.MockFactory;
import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoMapperTest extends FoursaleDesafioApplicationTests{

    @Autowired
    private MockFactory mockFactory;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Nested
    class Dado_uma_entidade extends FoursaleDesafioApplicationTests {
        private Pagamento pagamento;

        @BeforeEach
        void setup() {
            pagamento = mockFactory.construirPagamento(null);
        }

        @Nested
        class Quando_ser_convertido_para_dto extends FoursaleDesafioApplicationTests {
            private PagamentoDto pagamentoDto;

            @BeforeEach
            void setup() {
                pagamentoDto = pagamentoMapper.toDto(pagamento);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(pagamentoDto.getPedidoId());
                assertEquals(pagamento.getPedidoId(), pagamentoDto.getPedidoId());
            }
        }
    }

    @Nested
    class Dado_um_dto extends FoursaleDesafioApplicationTests {
        private PagamentoDto pagamentoDto;

        @BeforeEach
        void setup() {
            pagamentoDto = mockFactory.construirPagamentoDto(null);
        }

        @Nested
        class Quando_ser_convertido_para_entidade extends FoursaleDesafioApplicationTests {
            private Pagamento pagamento;

            @BeforeEach
            void setup() {
                pagamento = pagamentoMapper.toEntity(pagamentoDto);
            }

            @Test
            void Entao_deve_ser_transformado_com_sucesso() {
                assertNotNull(pagamentoDto.getPedidoId());
                assertEquals(pagamento.getPedidoId(), pagamentoDto.getPedidoId());
            }
        }
    }
}