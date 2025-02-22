package com.foursales.foursale_desafio.domain.model.categoria;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "subcategorias")
@Entity
public class Subcategoria extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String descricao;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "subcategoria")
    private List<Produto> produtos = new LinkedList<>();
}
