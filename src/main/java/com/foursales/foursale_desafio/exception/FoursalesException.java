package com.foursales.foursale_desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FoursalesException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Erro interno");
        return pb;
    }
}
