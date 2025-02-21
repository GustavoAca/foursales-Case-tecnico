package com.foursales.foursale_desafio.domain.mapper.categoria;

import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDto categoriaDto){
        return Categoria.builder()
                .id(categoriaDto.getId())
                .descricao(categoriaDto.getDescricao())
                .nome(categoriaDto.getNome())
                .criadoPor(categoriaDto.getCriadoPor())
                .dataDeCriacao(categoriaDto.getDataDeCriacao())
                .modificadoPor(categoriaDto.getModificadoPor())
                .dataDeModificacao(categoriaDto.getDataDeModificacao())
                .build();
    }

    public CategoriaDto toDto(Categoria categoria){
        return CategoriaDto.builder()
                .id(categoria.getId())
                .descricao(categoria.getDescricao())
                .nome(categoria.getNome())
                .criadoPor(categoria.getCriadoPor())
                .dataDeCriacao(categoria.getDataDeCriacao())
                .modificadoPor(categoria.getModificadoPor())
                .dataDeModificacao(categoria.getDataDeModificacao())
                .build();
    }
}
