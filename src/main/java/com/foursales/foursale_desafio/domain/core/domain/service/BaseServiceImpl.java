package com.foursales.foursale_desafio.domain.core.domain.service;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseServiceImpl<E extends EntityAbstract, K extends Serializable, R extends JpaRepository<E, K>> implements BaseService<E, K> {

    protected R repo;

    protected BaseServiceImpl(R repo) {
        this.repo = repo;
    }

    public E salvar(E entity) {
        return this.repo.save(entity);
    }

    @Override
    public ResponsePage<E> listarPagina(Pageable pageable) {
        return new ResponsePage<>(repo.findAll(pageable));
    }

    @Override
    public Optional<E> buscarPorId(K id) {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public void deletar(K id) {
        repo.deleteById(id);
    }

    @Override
    public Boolean exist(K id) {
        return repo.existsById(id);
    }

    @Override
    public <T, D> ResponsePage<D> mapearPage(Page<T> page, Function<T, D> mapper) {
        List<D> contentDto = page.getContent().stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new ResponsePage<>(contentDto, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    @Override
    public <T> ResponsePage<T> mapearPageSimples(Page<T> page) {
        return new ResponsePage<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements());
    }
}
