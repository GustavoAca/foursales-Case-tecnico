package com.foursales.foursale_desafio.domain.service.produto;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseService;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.model.produto.Produto;

import java.util.UUID;

public interface ProdutoService extends BaseService<Produto, UUID> {

    ProdutoDto atualizar(ProdutoDto produtoDto, UUID id);
}
