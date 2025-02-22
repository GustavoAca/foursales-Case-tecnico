package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.pedido.GastoMedioUsuario;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, UUID> {

    @Query("SELECT p FROM Pedido p where p.usuario.id = :usuarioId")
    Page<Pedido> findByUsuarioId(UUID usuarioId, Pageable pageable);

    @Query("SELECT p.usuario.id AS usuarioId, AVG(p.valorTotal) AS ticketMedio " +
            "FROM Pedido p " +
            "GROUP BY p.usuario.id")
    Page<GastoMedioUsuario> findValorGastoMedioPorUsuario(Pageable pageable);
}
