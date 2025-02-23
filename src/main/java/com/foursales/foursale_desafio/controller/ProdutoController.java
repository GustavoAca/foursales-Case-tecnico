package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.service.produto.ProdutoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ProdutoDto buscarPorId(@PathVariable UUID id) {
        return produtoService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<ProdutoDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return produtoService.listarPaginado(pageable);
    }

    @GetMapping("/existe-estoque")
    public Boolean listar(@RequestParam UUID id,
                          @RequestParam Integer quantidadeDesejada) {
        return produtoService.hasEstoqueDisponivel(id, quantidadeDesejada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        produtoService.deletar(id);
    }

    @PostMapping
    public ProdutoDto criar(@RequestBody ProdutoDto produtoDto) {
        return produtoService.criar(produtoDto);
    }

    @PutMapping("/atualizar/{id}")
    public ProdutoDto atualizar(@RequestBody ProdutoDto produtoDto,
                                @PathVariable UUID id) {
        return produtoService.atualizar(id, produtoDto);
    }
}
