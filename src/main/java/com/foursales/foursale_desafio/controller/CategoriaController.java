package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public CategoriaDto buscarPorId(@PathVariable UUID id) {
        return categoriaService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<CategoriaDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return categoriaService.listarPaginado(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        categoriaService.deletar(id);
    }

    @PostMapping
    public CategoriaDto criar(@RequestBody CategoriaDto categoriaDto) {
        return categoriaService.criar(categoriaDto);
    }

    @PutMapping("/atualizar/{id}")
    public CategoriaDto atualizar(@RequestBody CategoriaDto categoriaDto,
                                  @PathVariable UUID id) {
        return categoriaService.atualizar(id, categoriaDto);
    }
}
