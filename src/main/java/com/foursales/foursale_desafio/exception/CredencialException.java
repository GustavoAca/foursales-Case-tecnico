package com.foursales.foursale_desafio.exception;

import com.foursales.foursale_desafio.domain.core.exception.FoursalesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public final class CredencialException extends FoursalesException {

    public CredencialException() {
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Usuário ou senha inválidos");
        pb.setDetail("Usuário ou senha inválidos");
        return pb;
    }
}
