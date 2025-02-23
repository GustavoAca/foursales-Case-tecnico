package com.foursales.foursale_desafio.domain.service.categoria;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.categoria.CategoriaMapper;
import com.foursales.foursale_desafio.domain.mapper.dto.CategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import com.foursales.foursale_desafio.domain.repository.CategoriaRepository;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, UUID, CategoriaRepository> implements CategoriaService {

    private final CategoriaMapper categoriaMapper;

    protected CategoriaServiceImpl(CategoriaRepository repo, CategoriaMapper categoriaMapper) {
        super(repo);
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaDto criar(CategoriaDto categoriaDto) {
        return categoriaMapper.toDto(salvar(categoriaMapper.toEntity(categoriaDto)));
    }

    @Override
    public ResponsePage<CategoriaDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), categoriaMapper::toDto);
    }

    @Override
    public CategoriaDto buscaPorId(UUID id) {
        Categoria categoria = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, "Categoria"));
        return categoriaMapper.toDto(categoria);
    }

    @Override
    public CategoriaDto atualizar(UUID id, CategoriaDto categoriaDto) {
        return buscarPorId(id).map(categoria -> {
            categoriaDto.setId(categoria.getId());
            Categoria categoriaAtualizada = salvar(categoriaMapper.toEntity(categoriaDto));
            return categoriaMapper.toDto(categoriaAtualizada);
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id, "Categoria"));
    }
}
