package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.service.subcategoria.SubcategoriaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    public SubcategoriaController(SubcategoriaService subcategoriaService) {
        this.subcategoriaService = subcategoriaService;
    }

    @GetMapping("/{id}")
    public SubcategoriaDto buscarPorId(@PathVariable UUID id) {
        return subcategoriaService.buscaPorId(id);
    }

    @GetMapping("/listar")
    public ResponsePage<SubcategoriaDto> listar(@PageableDefault(size = 20) Pageable pageable) {
        return subcategoriaService.listarPaginado(pageable);
    }

    @GetMapping("/listar-por-categoria/{categoriaId}")
    public ResponsePage<SubcategoriaDto> listarPorCategoria(@PathVariable UUID categoriaId,
                                                            @PageableDefault(size = 20) Pageable pageable) {
        return subcategoriaService.listarPorCategoriaId(categoriaId, pageable);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        subcategoriaService.deletar(id);
    }

    @PostMapping
    public SubcategoriaDto criar(@RequestBody SubcategoriaDto subcategoriaDto) {
        return subcategoriaService.criar(subcategoriaDto);
    }

    @PutMapping("/atualizar/{id}")
    public SubcategoriaDto atualizar(@RequestBody SubcategoriaDto subcategoriaDto,
                                     @PathVariable UUID id) {
        return subcategoriaService.atualizar(id, subcategoriaDto);
    }
}
