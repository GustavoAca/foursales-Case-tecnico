package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends BaseRepository<Produto, UUID> {
}
