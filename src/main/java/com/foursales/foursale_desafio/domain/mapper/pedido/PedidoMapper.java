package com.foursales.foursale_desafio.domain.mapper.pedido;

import com.foursales.foursale_desafio.domain.mapper.dto.PedidoDto;
import com.foursales.foursale_desafio.domain.mapper.usuario.UsuarioMapper;
import com.foursales.foursale_desafio.domain.model.pedido.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    private final UsuarioMapper usuarioMapper;

    public PedidoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public Pedido toEntity(PedidoDto pedidoDto) {
        return Pedido.builder()
                .id(pedidoDto.getId())
                .valorTotal(pedidoDto.getValorTotal())
                .status(pedidoDto.getStatus())
                .usuario(usuarioMapper.toEntity(pedidoDto.getUsuario()))
                .criadoPor(pedidoDto.getCriadoPor())
                .dataDeCriacao(pedidoDto.getDataDeCriacao())
                .modificadoPor(pedidoDto.getModificadoPor())
                .dataDeModificacao(pedidoDto.getDataDeModificacao())
                .build();
    }

    public PedidoDto toDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .usuario(usuarioMapper.toDto(pedido.getUsuario()))
                .criadoPor(pedido.getCriadoPor())
                .dataDeCriacao(pedido.getDataDeCriacao())
                .modificadoPor(pedido.getModificadoPor())
                .dataDeModificacao(pedido.getDataDeModificacao())
                .build();
    }
}
