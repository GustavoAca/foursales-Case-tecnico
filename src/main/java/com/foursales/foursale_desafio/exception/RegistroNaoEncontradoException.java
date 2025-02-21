package com.foursales.foursale_desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import com.foursales.foursale_desafio.domain.core.exception.FoursalesException;

import java.io.Serializable;

public class RegistroNaoEncontradoException extends FoursalesException {
    private final Serializable id;
    private final String entidade;

    public RegistroNaoEncontradoException(Serializable id, String entidade) {
        this.id = id;
        this.entidade = entidade;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Registro não encontrado.");
        pb.setDetail(String.format("%s não encontrado com o id: %s.", entidade, id));
        return pb;
    }
}
