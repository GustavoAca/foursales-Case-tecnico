package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import com.foursales.foursale_desafio.domain.repository.PagamentoRepository;
import com.foursales.foursale_desafio.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoServiceImpl extends BaseServiceImpl<Pagamento, UUID, PagamentoRepository> implements PagamentoService {

    protected PagamentoServiceImpl(PagamentoRepository repo) {
        super(repo);
    }
}
