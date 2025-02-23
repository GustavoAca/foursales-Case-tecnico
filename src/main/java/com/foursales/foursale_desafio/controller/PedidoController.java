package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoPedidoDto;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.service.usuario.UsuarioComponent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final UsuarioComponent usuarioComponent;

    public PedidoController(
            UsuarioComponent usuarioComponent) {
        this.usuarioComponent = usuarioComponent;
    }

    @GetMapping("/{id}")
    public PedidoDto buscarPorId(@PathVariable UUID id) {
        return usuarioComponent.buscarPedidoPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<PedidoDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return usuarioComponent.listarPedidoPaginado(pageable);
    }

    @GetMapping("/listar-por-usuario/{usuarioId}")
    public ResponsePage<PedidoDto> listarPorUsuario(@PathVariable UUID usuarioId,
                                                    @PageableDefault(size = 20) Pageable pageable) {
        return usuarioComponent.listarPedidoPorUsuarioIdPaginado(usuarioId, pageable);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        usuarioComponent.deletarPedido(id);
    }

    @PostMapping
    public PedidoDto criar(@RequestBody PedidoDto pedidoDto) {
        return usuarioComponent.criarPedido(pedidoDto);
    }

    @PutMapping("/{id}/atualizar")
    public PedidoDto atualizar(@RequestBody PedidoDto pedidoDto,
                               @PathVariable UUID id) {
        return usuarioComponent.atualizarPedido(id, pedidoDto);
    }

    @PostMapping("/{id}/adicionar-produto")
    public void adicionarProduto(@RequestBody List<ProdutoPedidoDto> pedidoDto,
                                 @PathVariable UUID id) {
        usuarioComponent.adicionarProdutoPedido(pedidoDto, id);
    }

    @PutMapping("/{id}/atualizar-status")
    public PedidoDto atualizarStatus(@PathVariable UUID id,
                                     @RequestParam Status status) {
        return usuarioComponent.atualizarStatusDePedido(id, status);
    }

    @GetMapping("/{id}/disponivel-para-pagamento")
    public Boolean isDisponivelParaPagamento(@PathVariable UUID id) {
        return usuarioComponent.isPedidoDisponivelParaPagamento(id);
    }

    @DeleteMapping("/remover-produtos")
    public void removerProdutos(@RequestBody List<UUID> ids) {
        usuarioComponent.removerProdutoPedido(ids);
    }

    @PutMapping("/{id}/atualizar-total-de-compras")
    public void atualizarComprasDeUsuarioPorPedidoId(@PathVariable UUID id,
                                                     @RequestParam int quantidadeDeItensComprados) {
        usuarioComponent.atualizarComprasDeUsuarioPorPedidoId(id, quantidadeDeItensComprados);
    }
}
