package com.foursales.foursale_desafio.domain.repository;

import com.foursales.foursale_desafio.domain.core.domain.repository.BaseRepository;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.repository.projection.FaturamentoProjection;
import com.foursales.foursale_desafio.domain.repository.projection.GastoMedioUsuarioProjection;
import com.foursales.foursale_desafio.domain.repository.projection.PedidoStatusProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, UUID> {

    @Query("SELECT p FROM Pedido p where p.usuario.id = :usuarioId")
    Page<Pedido> findByUsuarioId(@Param("usuarioId") UUID usuarioId, Pageable pageable);

    @Query("SELECT AVG(p.valorTotal) AS mediaDeGasto, " +
            "p.usuario.id AS usuarioId " +
            "FROM Pedido p " +
            "GROUP BY p.usuario.id")
    Page<GastoMedioUsuarioProjection> findValorGastoMedioPorUsuario(Pageable pageable);

    @Query("SELECT p.status AS status FROM Pedido p WHERE p.id = :id")
    Optional<PedidoStatusProjection> findStatusById(@Param("id") UUID id);

    @Query("SELECT YEAR(p.dataDeCriacao) AS ano, " +
            "MONTH(p.dataDeCriacao) AS mes, " +
            "SUM(p.valorTotal) AS faturamento " +
            "FROM Pedido p " +
            "WHERE YEAR(p.dataDeCriacao) = :anoReferencia " +
            "AND MONTH(p.dataDeCriacao) = :mesReferencia " +
            "GROUP BY YEAR(p.dataDeCriacao), MONTH(p.dataDeCriacao)")
    Page<FaturamentoProjection> findByFaturamentoMensal(@Param("mesReferencia") int mesReferencia,
                                                        @Param("anoReferencia") int anoReferencia,
                                                        Pageable pageable);

}
