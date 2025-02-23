package com.foursales.foursale_desafio.controller;


import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.service.pagamento.PagamentoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentroController {

    private final PagamentoService pagamentoService;

    public PagamentroController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/{id}")
    public PagamentoDto buscarPorId(@PathVariable UUID id) {
        return pagamentoService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<PagamentoDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return pagamentoService.listarPaginado(pageable);
    }

    @PostMapping("/pedidos/{pedidoId}")
    public Boolean pagar(@PathVariable UUID pedidoId) {
        return pagamentoService.realizarPagamento(pedidoId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        pagamentoService.deletar(id);
    }
}
