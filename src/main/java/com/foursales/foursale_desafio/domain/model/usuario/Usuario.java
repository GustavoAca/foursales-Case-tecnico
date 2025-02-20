package com.foursales.foursale_desafio.domain.model.usuario;

import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "usuarios")
@Entity
public class Usuario extends EntityAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    private String nome;
    @Column(name = "total_de_compras_realizadas")
    @Builder.Default
    private Integer totalDeComprasRealizadas = 0;

}
