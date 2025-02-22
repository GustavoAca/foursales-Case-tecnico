package com.foursales.foursale_desafio.domain.service.produto;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProdutoService extends BaseService<Produto, UUID> {

    ProdutoDto criar(ProdutoDto produtoDto);

    ResponsePage<ProdutoDto> listarPaginado(Pageable pageable);

    ProdutoDto buscaPorId(UUID id);

    ProdutoDto atualizar(UUID id, ProdutoDto produtoDto);
}
