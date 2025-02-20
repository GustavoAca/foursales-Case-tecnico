package com.foursales.foursale_desafio.domain.service.pedido;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, UUID, PedidoRepository> implements PedidoService {

    protected PedidoServiceImpl(PedidoRepository repo) {
        super(repo);
    }
}
