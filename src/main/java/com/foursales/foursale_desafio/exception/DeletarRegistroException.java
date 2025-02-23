package com.foursales.foursale_desafio.exception;

import com.foursales.foursale_desafio.domain.core.exception.FoursalesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class DeletarRegistroException extends FoursalesException {

    private final String mensagem;
    private final List<UUID> ids;

    public DeletarRegistroException(String mensagem,
                                    List<UUID> ids) {
        this.mensagem = mensagem;
        this.ids = ids;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("Erro ao deletar registro");
        pb.setDetail(formatarTexto());
        return pb;
    }

    private String formatarTexto() {
        StringJoiner joiner = new StringJoiner(",");
        for (Serializable id : this.ids) {
            joiner.add(id.toString());
        }
        return String.format(this.mensagem, joiner);
    }
}
