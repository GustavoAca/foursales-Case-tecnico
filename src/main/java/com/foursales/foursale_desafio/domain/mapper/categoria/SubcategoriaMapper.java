package com.foursales.foursale_desafio.domain.mapper.categoria;

import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import org.springframework.stereotype.Component;

@Component
public class SubcategoriaMapper  {

    public Subcategoria toEntity(SubcategoriaDto subcategoriaDto){
        return Subcategoria.builder()
                .id(subcategoriaDto.getId())
                .descricao(subcategoriaDto.getDescricao())
                .nome(subcategoriaDto.getNome())
                .categoria(toCategoriaEntity(subcategoriaDto.getCategoria()))
                .criadoPor(subcategoriaDto.getCriadoPor())
                .dataDeCriacao(subcategoriaDto.getDataDeCriacao())
                .modificadoPor(subcategoriaDto.getModificadoPor())
                .dataDeModificacao(subcategoriaDto.getDataDeModificacao())
                .build();
    }

    private Categoria toCategoriaEntity(CategoriaDto categoriaDto){
        return Categoria.builder().id(categoriaDto.getId()).build();
    }

    public SubcategoriaDto toDto(Subcategoria subcategotia){
        return SubcategoriaDto.builder()
                .id(subcategotia.getId())
                .descricao(subcategotia.getDescricao())
                .nome(subcategotia.getNome())
                .categoria(toCategoriaDto(subcategotia.getCategoria()))
                .criadoPor(subcategotia.getCriadoPor())
                .dataDeCriacao(subcategotia.getDataDeCriacao())
                .modificadoPor(subcategotia.getModificadoPor())
                .dataDeModificacao(subcategotia.getDataDeModificacao())
                .build();
    }

    private CategoriaDto toCategoriaDto(Categoria categoria){
        return CategoriaDto.builder().id(categoria.getId()).build();
    }
}
