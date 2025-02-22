package com.foursales.foursale_desafio.domain.model.produto;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "produto_pedido")
@Entity
public class ProdutoPedido extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;

    @Builder.Default
    private BigDecimal preco = BigDecimal.ZERO;
}
