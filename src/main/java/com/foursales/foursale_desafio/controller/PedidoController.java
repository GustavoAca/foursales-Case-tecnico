package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{id}")
    public PedidoDto buscarPorId(@PathVariable UUID id) {
        return pedidoService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<PedidoDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return pedidoService.listarPaginado(pageable);
    }

    @GetMapping("/listar-por-usuario/{usuarioId}")
    public ResponsePage<PedidoDto> listarPorUsuario(@PathVariable UUID usuarioId,
                                                    @PageableDefault(size = 20) Pageable pageable) {
        return pedidoService.listarPaginado(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        pedidoService.deletar(id);
    }

    @PostMapping
    public PedidoDto criar(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.criar(pedidoDto);
    }

    @PutMapping("/{id}/atualizar")
    public PedidoDto atualizar(@RequestBody PedidoDto pedidoDto,
                               @PathVariable UUID id) {
        return pedidoService.atualizar(id, pedidoDto);
    }

    @PostMapping("/{id}/adicionar-produto")
    public void adicionarProduto(@RequestBody List<ProdutoPedidoDto> pedidoDto,
                                 @PathVariable UUID id) {
        pedidoService.adicionarProduto(pedidoDto, id);
    }

    @PutMapping("/{id}/atualizar-status")
    public PedidoDto atualizarStatus(@PathVariable UUID id,
                                     @RequestBody Status status) {
        return pedidoService.atualizarStatus(id, status);
    }

    @GetMapping("/{id}/disponivel-para-pagamento")
    public Boolean isDisponivelParaPagamento(@PathVariable UUID id) {
        return pedidoService.isDisponivelParaPagamento(id);
    }

    @DeleteMapping("/remover-produtos")
    public void removerProdutos(@RequestBody List<UUID> ids) {
        pedidoService.removerProduto(ids);
    }

    //todo
//    @PutMapping("/{id}/atualizar-total-de-compras")
//    public void atualizarComprasDeUsuarioPorPedidoId(@PathVariable UUID id,
//                                                     @RequestParam int quantidadeDeItensComprados) {
//        pedidoService.atualizarComprasDeUsuarioPorPedidoId(id, quantidadeDeItensComprados);
//    }
}
