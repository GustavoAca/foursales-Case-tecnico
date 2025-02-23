package com.foursales.foursale_desafio.domain.service.subcategoria;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.categoria.SubcategoriaMapper;
import com.foursales.foursale_desafio.domain.mapper.dto.SubcategoriaDto;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import com.foursales.foursale_desafio.domain.repository.SubcategoriaRepository;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubcategoriaServiceImpl extends BaseServiceImpl<Subcategoria, UUID, SubcategoriaRepository> implements SubcategoriaService {

    private final SubcategoriaMapper subcategoriaMapper;

    protected SubcategoriaServiceImpl(SubcategoriaRepository repo,
                                      SubcategoriaMapper subcategoriaMapper) {
        super(repo);
        this.subcategoriaMapper = subcategoriaMapper;
    }

    @Override
    public SubcategoriaDto criar(SubcategoriaDto subcategoriaDto) {
        return subcategoriaMapper.toDto(salvar(subcategoriaMapper.toEntity(subcategoriaDto)));
    }

    @Override
    public ResponsePage<SubcategoriaDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), subcategoriaMapper::toDto);
    }

    @Override
    public SubcategoriaDto buscaPorId(UUID id) {
        Subcategoria subcategoria = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, "Subcategoria"));
        return subcategoriaMapper.toDto(subcategoria);
    }

    @Override
    public SubcategoriaDto atualizar(UUID id, SubcategoriaDto subcategoriaDto) {
        return buscarPorId(id).map(subcategoria -> {
            subcategoriaDto.setId(subcategoria.getId());
            Subcategoria categoriaAtualizada = salvar(subcategoriaMapper.toEntity(subcategoriaDto));
            return subcategoriaMapper.toDto(categoriaAtualizada);
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id, "Subcategoria"));
    }

    @Override
    public ResponsePage<SubcategoriaDto> listarPorCategoriaId(UUID categoriaId, Pageable pageable) {
        return mapearPage(repo.findByCategoriaId(categoriaId, pageable), subcategoriaMapper::toDto);
    }
}
