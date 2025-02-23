package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends BaseRepository<Produto, UUID> {

    @Query("SELECT new java.lang.Boolean(CASE WHEN p.quantidadeEmEstoque >= :quantidadeDesejada THEN true ELSE false END) FROM Produto p WHERE p.id = :id")
    Boolean hasEstoqueDisponivel(@Param("id") UUID id, @Param("quantidadeDesejada") Integer quantidadeDesejada);
}
