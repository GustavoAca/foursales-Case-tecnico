package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.repository.PagamentoRepository;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoServiceImpl extends BaseServiceImpl<Pagamento, UUID, PagamentoRepository> implements PagamentoService {

    private final PedidoService pedidoService;
    private final ProdutoPedidoService produtoPedidoService;

    protected PagamentoServiceImpl(PagamentoRepository repo,
                                   PedidoService pedidoService,
                                   ProdutoPedidoService produtoPedidoService) {
        super(repo);
        this.pedidoService = pedidoService;
        this.produtoPedidoService = produtoPedidoService;
    }

    @Override
    public Boolean realizarPagamento(UUID pedidoId) {
        if (pedidoService.isDisponivelParaPagamento(pedidoId)) {
            pedidoService.atualizarStatus(pedidoId, Status.CONFIRMADO);
            int quantidadeDeProdutosComprados = produtoPedidoService.atualizarEstoque(pedidoId);
            pedidoService.atualizarComprasDeUsuarioPorCompraId(pedidoId, quantidadeDeProdutosComprados);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
