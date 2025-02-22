package com.foursales.foursale_desafio.domain.service.subcategoria;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SubcategoriaService extends BaseService<Subcategoria, UUID> {

    SubcategoriaDto criar(SubcategoriaDto subcategoriaDto);

    ResponsePage<SubcategoriaDto> listarPaginado(Pageable pageable);

    SubcategoriaDto buscaPorId(UUID id);

    SubcategoriaDto atualizar(UUID id, SubcategoriaDto subcategoriaDto);

    ResponsePage<SubcategoriaDto> listarPorCategoriaId(UUID categoriaId, Pageable pageable);
}
