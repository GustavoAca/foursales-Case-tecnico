package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produtos-pedidos")
public class ProdutoPedidoController {

    private final ProdutoPedidoService produtoPedidoService;

    public ProdutoPedidoController(ProdutoPedidoService produtoPedidoService) {
        this.produtoPedidoService = produtoPedidoService;
    }

    @GetMapping("/{id}")
    public ProdutoPedidoDto buscarPorId(@PathVariable UUID id) {
        return produtoPedidoService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<ProdutoPedidoDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return produtoPedidoService.listarPaginado(pageable);
    }

    @GetMapping("/listar-por-pedido/{pedidoId}")
    public ResponsePage<ProdutoPedidoDto> listar(@PathVariable UUID pedidoId,
                                                 @PageableDefault(size = 20) Pageable pageable) {
        return produtoPedidoService.listarPaginadoPorPedidoId(pedidoId, pageable);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        produtoPedidoService.deletar(id);
    }

    @PostMapping
    public ProdutoPedidoDto criar(@RequestBody ProdutoPedidoDto produtoPedidoDto) {
        return produtoPedidoService.criar(produtoPedidoDto);
    }

    @PutMapping("/atualizar/{id}")
    public ProdutoPedidoDto atualizar(@RequestBody ProdutoPedidoDto produtoPedidoDto,
                                      @PathVariable UUID id) {
        return produtoPedidoService.atualizar(id, produtoPedidoDto);
    }

    @PutMapping("/atualizar-estoque/{pedidoId}")
    public Integer atualizarEstoque(@PathVariable UUID pedidoId) {
        return produtoPedidoService.atualizarEstoque(pedidoId);
    }
}
