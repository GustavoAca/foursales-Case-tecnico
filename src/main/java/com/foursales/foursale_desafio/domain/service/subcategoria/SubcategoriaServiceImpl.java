package com.foursales.foursale_desafio.domain.service.subcategoria;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.categoria.Subcategoria;
import com.foursales.foursale_desafio.domain.repository.SubcategoriaRepository;
import com.foursales.foursale_desafio.domain.service.categoria.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubcategoriaServiceImpl extends BaseServiceImpl<Subcategoria, UUID, SubcategoriaRepository> implements SubcategoriaService {

    protected SubcategoriaServiceImpl(SubcategoriaRepository repo) {
        super(repo);
    }
}
