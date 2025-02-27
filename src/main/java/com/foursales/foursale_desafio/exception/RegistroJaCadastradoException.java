package com.foursales.foursale_desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import com.foursales.foursale_desafio.domain.core.exception.FoursalesException;


public class RegistroJaCadastradoException extends FoursalesException {
    private final String mensagem;

    public RegistroJaCadastradoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("Registro já cadastrado");
        pb.setDetail(mensagem);
        return pb;
    }
}
