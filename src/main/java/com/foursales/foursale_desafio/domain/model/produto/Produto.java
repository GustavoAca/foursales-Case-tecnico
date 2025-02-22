package com.foursales.foursale_desafio.domain.model.produto;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Produto extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String descricao;
    private String nome;
    private BigDecimal preco;

    @Column(name = "quantidade_em_estoque")
    private Integer quantidadeEmEstoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutoPedido> itens = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria;
}
