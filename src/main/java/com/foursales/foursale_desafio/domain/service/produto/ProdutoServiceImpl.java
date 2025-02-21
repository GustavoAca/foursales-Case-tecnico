package com.foursales.foursale_desafio.domain.service.produto;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoServiceImpl extends BaseServiceImpl<Produto, UUID, ProdutoRepository> implements ProdutoService {
    private final ProdutoMapper produtoMapper;

    protected ProdutoServiceImpl(ProdutoRepository repo,
                                 ProdutoMapper produtoMapper) {
        super(repo);
        this.produtoMapper = produtoMapper;
    }
}
