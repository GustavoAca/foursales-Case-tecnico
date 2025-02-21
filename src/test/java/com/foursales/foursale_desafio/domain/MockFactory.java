package com.foursales.foursale_desafio.domain;

import com.foursales.foursale_desafio.domain.mapper.dto.*;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.model.usuario.Perfil;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class MockFactory {

    public ProdutoDto construirProdutoDto(){
        return ProdutoDto.builder()
                .id(UUID.randomUUID())
                .nome("Moto G54")
                .descricao("Celular motorola moto G54, 256GB de armazenamento")
                .preco(BigDecimal.valueOf(26.00))
                .subcategoriaDto(construirSubcategoriaDto())
                .quantidadeEmEstoque(10)
                .build();
    }

    public SubcategoriaDto construirSubcategoriaDto(){
        return SubcategoriaDto.builder()
                .id(UUID.randomUUID())
                .categoria(construirCategoriaDto())
                .nome("Eletrônicos")
                .descricao("Categoria de eletrônicos")
                .build();
    }

    public CategoriaDto construirCategoriaDto(){
        return CategoriaDto.builder()
                .id(UUID.randomUUID())
                .nome("Eletrônicos")
                .descricao("Categoria de eletrônicos")
                .build();
    }

    public Subcategoria construirSubcategoria(){
        return Subcategoria.builder()
                .id(UUID.randomUUID())
                .categoria(construirCategoria())
                .nome("Eletrônicos")
                .descricao("Categoria de eletrônicos")
                .build();
    }

    public Categoria construirCategoria(){
        return Categoria.builder()
                .id(UUID.randomUUID())
                .nome("Eletrônicos")
                .descricao("Categoria de eletrônicos")
                .build();
    }

    public Produto construirProduto(){
        return Produto.builder()
                .id(UUID.randomUUID())
                .nome("Moto G54")
                .descricao("Celular motorola moto G54, 256GB de armazenamento")
                .preco(BigDecimal.valueOf(26.00))
                .subcategoria(construirSubcategoria())
                .quantidadeEmEstoque(10)
                .build();
    }

    public Pedido construirPedido(UUID id){
        List<Produto> listaDeProdutos = construirListaDeProdutos();
        return Pedido.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .produtos(listaDeProdutos)
                .usuario(construirUsuario())
                .valorTotal(listaDeProdutos.stream()
                        .map(Produto::getPreco)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    public List<Produto> construirListaDeProdutos(){
        List<Produto> listaDeProdutos = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            listaDeProdutos.add(construirProduto());
        }
        return listaDeProdutos;
    }

    public Usuario construirUsuario(){
        return Usuario.builder()
                .id(UUID.randomUUID())
                .email("jorge@foursales.com")
                .nome("Jorge")
                .senha("1234")
                .perfil(Perfil.USER)
                .build();
    }

    public PedidoDto construirPedidoDto(UUID id){
        List<ProdutoDto> listaDeProdutosDto = construirListaDeProdutosDto();
        return PedidoDto.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .produtos(listaDeProdutosDto)
                .usuario(construirUsuario())
                .valorTotal(listaDeProdutosDto.stream()
                        .map(ProdutoDto::getPreco)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    public List<ProdutoDto> construirListaDeProdutosDto(){
        List<ProdutoDto> listaDeProdutos = new LinkedList<>();
        for(int i = 0; i <= 3; i++){
            listaDeProdutos.add(construirProdutoDto());
        }
        return listaDeProdutos;
    }

    public PagamentoDto construirPagamentoDto(UUID pedidoId){
        return PagamentoDto.builder()
                .pedidoId(construirPedidoDto(pedidoId).getId())
                .build();
    }

    public Pagamento construirPagamento(UUID pedidoId){
        return Pagamento.builder()
                .pedidoId(construirPedidoDto(pedidoId).getId())
                .build();
    }
}
