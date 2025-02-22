package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.repository.PagamentoRepository;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoServiceImpl extends BaseServiceImpl<Pagamento, UUID, PagamentoRepository> implements PagamentoService {

    private final PedidoService pedidoService;

    protected PagamentoServiceImpl(PagamentoRepository repo, PedidoService pedidoService) {
        super(repo);
        this.pedidoService = pedidoService;
    }

    @Override
    public Boolean realizarPagamento(UUID pedidoId) {
        if (pedidoService.isTodosProdutosPedidosTemEstoque(pedidoId)) {

        }
        return null;
    }
}
