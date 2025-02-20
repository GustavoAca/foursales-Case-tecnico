package com.foursales.foursale_desafio.domain.service.categoria;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.categoria.Categoria;
import com.foursales.foursale_desafio.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, UUID, CategoriaRepository> implements CategoriaService {

    protected CategoriaServiceImpl(CategoriaRepository repo) {
        super(repo);
    }
}
