package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubcategoriaRepository extends BaseRepository<Subcategoria, UUID> {
}
