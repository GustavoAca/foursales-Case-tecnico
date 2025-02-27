package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubcategoriaRepository extends BaseRepository<Subcategoria, UUID> {

    @Query("SELECT s FROM Subcategoria s WHERE s.categoria.id = :categoriaId")
    Page<Subcategoria> findByCategoriaId(UUID categoriaId, Pageable pageable);
}
