package com.foursales.foursale_desafio.domain;

import com.foursales.foursale_desafio.domain.core.security.Perfil;
import com.foursales.foursale_desafio.domain.mapper.dto.*;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.model.produto.ProdutoPedido;
import com.foursales.foursale_desafio.domain.model.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

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
        return Pedido.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .usuario(construirUsuario())
                .build();
    }

    public ProdutoPedido construirProdutoPedido(UUID id) {
        return ProdutoPedido.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .quantidade(2)
                .preco(BigDecimal.ONE)
                .pedido(construirPedido(null))
                .produto(construirProduto())
                .build();
    }

    public ProdutoPedido construirProdutoPedidoComListaExistente() {
        return ProdutoPedido.builder()
                .quantidade(2)
                .preco(BigDecimal.ONE)
                .produto(construirProduto())
                .build();
    }

    public ProdutoPedidoDto construirProdutoPedidoDto(UUID id) {
        return ProdutoPedidoDto.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .quantidade(2)
                .preco(BigDecimal.ONE)
                .pedido(construirPedidoDto(null))
                .produto(construirProdutoDto())
                .build();
    }

    public Usuario construirUsuario(){
        return Usuario.builder()
                .id(UUID.randomUUID())
                .email(String.format("gustavo%d@foursales.com", new Random().nextInt(100000) + 1))
                .nome("Gustavo")
                .senha("1234")
                .perfil(Perfil.ROLE_USER)
                .build();
    }

    public PedidoDto construirPedidoDto(UUID id){
        return PedidoDto.builder()
                .id(Objects.nonNull(id) ? id : UUID.randomUUID())
                .usuario(construirUsuarioDto())
                .build();
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

    public UsuarioDto construirUsuarioDto() {
        return UsuarioDto.builder()
                .id(UUID.randomUUID())
                .email(String.format("gustavo%d@foursales.com", new Random().nextInt(100000) + 1))
                .nome("Gustavo")
                .senha("1234")
                .perfil(Perfil.ROLE_USER)
                .build();
    }
}
