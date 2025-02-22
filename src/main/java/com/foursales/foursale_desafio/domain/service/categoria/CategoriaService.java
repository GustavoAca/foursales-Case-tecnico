package com.foursales.foursale_desafio.domain.service.categoria;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoriaService extends BaseService<Categoria, UUID> {

    CategoriaDto criar(CategoriaDto categoriaDto);

    ResponsePage<CategoriaDto> listarPaginado(Pageable pageable);

    CategoriaDto buscaPorId(UUID id);

    CategoriaDto atualizar(UUID id, CategoriaDto categoriaDto);
}
