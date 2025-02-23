package com.foursales.foursale_desafio.domain.service.pagamento;

import com.foursales.foursale_desafio.domain.core.domain.ResponsePage;
import com.foursales.foursale_desafio.domain.core.domain.service.BaseServiceImpl;
import com.foursales.foursale_desafio.domain.mapper.dto.PagamentoDto;
import com.foursales.foursale_desafio.domain.mapper.pagamento.PagamentoMapper;
import com.foursales.foursale_desafio.domain.model.pagamento.Pagamento;
import com.foursales.foursale_desafio.domain.model.pedido.Status;
import com.foursales.foursale_desafio.domain.repository.PagamentoRepository;
import com.foursales.foursale_desafio.domain.service.pedido.PedidoService;
import com.foursales.foursale_desafio.domain.service.produtopedido.ProdutoPedidoService;
import com.foursales.foursale_desafio.domain.service.usuario.TokenComponent;
import com.foursales.foursale_desafio.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoServiceImpl extends BaseServiceImpl<Pagamento, UUID, PagamentoRepository> implements PagamentoService {

    private final PedidoService pedidoService;
    private final ProdutoPedidoService produtoPedidoService;
    private final PagamentoMapper pagamentoMapper;
    private final TokenComponent tokenComponent;

    protected PagamentoServiceImpl(PagamentoRepository repo,
                                   PedidoService pedidoService,
                                   ProdutoPedidoService produtoPedidoService,
                                   PagamentoMapper pagamentoMapper,
                                   TokenComponent tokenComponent) {
        super(repo);
        this.pedidoService = pedidoService;
        this.produtoPedidoService = produtoPedidoService;
        this.pagamentoMapper = pagamentoMapper;
        this.tokenComponent = tokenComponent;
    }

    @Override
    public Boolean realizarPagamento(UUID pedidoId) {
        if (pedidoService.isDisponivelParaPagamento(pedidoId)) {
            pedidoService.atualizarStatus(pedidoId, Status.CONFIRMADO);
            int quantidadeDeProdutosComprados = produtoPedidoService.atualizarEstoque(pedidoId);
            tokenComponent.atualizarComprasDeUsuarioPorPedidoId(pedidoId, quantidadeDeProdutosComprados);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public PagamentoDto buscaPorId(UUID id) {
        Pagamento pagamento = buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id, Pagamento.class.getName()));
        return pagamentoMapper.toDto(salvar(pagamento));
    }

    @Override
    public ResponsePage<PagamentoDto> listarPaginado(Pageable pageable) {
        return mapearPage(listarPagina(pageable), pagamentoMapper::toDto);
    }
}
