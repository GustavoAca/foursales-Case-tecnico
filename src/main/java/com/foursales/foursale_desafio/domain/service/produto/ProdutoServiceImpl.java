package com.foursales.foursale_desafio.domain.service.produto;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.dto.ProdutoDto;
import com.foursales.foursale_desafio.domain.mapper.produto.ProdutoMapper;
import com.foursales.foursale_desafio.domain.model.produto.Produto;
import com.foursales.foursale_desafio.domain.repository.ProdutoRepository;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Pageable;
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

    public ProdutoDto criar(ProdutoDto produtoDto) {
        return produtoMapper.toDto(salvar(produtoMapper.toEntity(produtoDto)));
    }

    public ResponsePage<ProdutoDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), produtoMapper::toDto);
    }

    public ProdutoDto buscaPorId(UUID id) {
        Produto produto = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, ProdutoDto.class.getName()));
        return produtoMapper.toDto(produto);
    }

    public ProdutoDto atualizar(UUID id, ProdutoDto produtoDto) {
        return buscarPorId(id).map(produto -> {
            produtoDto.setId(produto.getId());
            Produto produtoAtualizado = salvar(produtoMapper.toEntity(produtoDto));
            return produtoMapper.toDto(produtoAtualizado);
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id, ProdutoDto.class.getName()));
    }

    @Override
    public Boolean hasEstoqueDisponivel(UUID id, Integer quantidadeDesejada) {
        return repo.hasEstoqueDisponivel(id, quantidadeDesejada);
    }
}
