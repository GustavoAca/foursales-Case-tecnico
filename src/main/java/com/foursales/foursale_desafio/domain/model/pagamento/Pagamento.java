package com.foursales.foursale_desafio.domain.model.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "pagamentos")
@Entity
public class Pagamento extends EntityAbstract {

    @Id
    private UUID pedidoId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Pedido pedido;
}
