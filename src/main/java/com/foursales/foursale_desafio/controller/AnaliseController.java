package com.foursales.foursale_desafio.controller;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.mapper.dto.UsuarioDto;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import com.foursales.foursale_desafio.domain.service.analise.AnaliseService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/analises")
public class AnaliseController {

    private final AnaliseService analiseService;

    public AnaliseController(AnaliseService analiseService) {
        this.analiseService = analiseService;
    }

    @GetMapping("/maiores-compradores")
    public ResponsePage<UsuarioDto> getMaioresCompradores(@PageableDefault(size = 20) Pageable pageable) {
        return analiseService.getMaioresCompradores(pageable);
    }

    @GetMapping("/gastos-medios-por-usuario")
    public ResponsePage<GastoMedioUsuarioProjection> getGastosMediosPorUsuario(@PageableDefault(size = 20) Pageable pageable) {
        return analiseService.getValorGastoMedioPorUsuario(pageable);
    }

    @GetMapping("/gastos-medios-por-usuario/{usuarioId}")
    public GastoMedioUsuarioProjection getGastosMediosPorUsuarioId(@PathVariable UUID usuarioId) {
        return analiseService.getValorGastoMedioPorUsuarioId(usuarioId);
    }

    @GetMapping("/faturamento-por-periodo")
    public ResponsePage<FaturamentoProjection> getFaturamentoPorPeriodo(int mesReferencia,
                                                                        int anoReferencia,
                                                                        @PageableDefault(size = 20) Pageable pageable) {
        return analiseService.getFaturamentoPorPeriodo(mesReferencia, anoReferencia, pageable);
    }
}
