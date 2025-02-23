package com.foursales.foursale_desafio.domain.mapper.produto;

import com.foursales.foursale_desafio.domain.mapper.categoria.SubcategoriaMapper;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final SubcategoriaMapper subcategoriaMapper;

    public ProdutoMapper(SubcategoriaMapper subcategoriaMapper) {
        this.subcategoriaMapper = subcategoriaMapper;
    }

    public Produto toEntity(ProdutoDto produtoDto) {
        return Produto.builder()
                .id(produtoDto.getId())
                .descricao(produtoDto.getDescricao())
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .quantidadeEmEstoque(produtoDto.getQuantidadeEmEstoque())
                .subcategoria(toSubcategoriaEntity(produtoDto.getSubcategoria()))
                .criadoPor(produtoDto.getCriadoPor())
                .dataDeCriacao(produtoDto.getDataDeCriacao())
                .modificadoPor(produtoDto.getModificadoPor())
                .dataDeModificacao(produtoDto.getDataDeModificacao())
                .build();
    }

    private Subcategoria toSubcategoriaEntity(SubcategoriaDto subcategoriaDto) {
        return subcategoriaMapper.toEntity(subcategoriaDto);
    }

    public ProdutoDto toDto(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .quantidadeEmEstoque(produto.getQuantidadeEmEstoque())
                .subcategoria(toSubcategoriaDto(produto.getSubcategoria()))
                .criadoPor(produto.getCriadoPor())
                .dataDeCriacao(produto.getDataDeCriacao())
                .modificadoPor(produto.getModificadoPor())
                .dataDeModificacao(produto.getDataDeModificacao())
                .build();
    }

    private SubcategoriaDto toSubcategoriaDto(Subcategoria subcategoria) {
        return subcategoriaMapper.toDto(subcategoria);
    }
}
