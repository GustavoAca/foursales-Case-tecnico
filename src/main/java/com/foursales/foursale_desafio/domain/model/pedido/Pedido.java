package com.foursales.foursale_desafio.domain.model.pedido;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "produtos")
@Entity
public class Pedido extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "valor_total")
    @Builder.Default
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Builder.Default
    private Status status = Status.PENDENTE;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pagamento pagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoPedido> produtoPedidos = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
