package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoPedidoRepository extends BaseRepository<ProdutoPedido, UUID> {

    @Query("SELECT pp FROM ProdutoPedido pp WHERE pp.pedido.id = :pedidoId")
    Page<ProdutoPedido> findByPedidoId(UUID pedidoId, Pageable pageable);
}
