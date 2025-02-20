package com.foursales.foursale_desafio.domain.core.domain.service;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.model.EntityAbstract;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<E extends EntityAbstract, K extends Serializable> {
    E salvar(E entity);

    ResponsePage<E> listarPagina(Pageable pageable);

    Optional<E> buscarPorId(K id);

    Boolean deletar(K id);
}
