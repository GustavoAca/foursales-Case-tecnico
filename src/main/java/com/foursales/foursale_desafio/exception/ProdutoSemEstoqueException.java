package com.foursales.foursale_desafio.exception;

import com.foursales.foursale_desafio.domain.core.exception.FoursalesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.StringJoiner;

public class ProdutoSemEstoqueException extends FoursalesException {

    private final List<String> produtosSemEstoque;
    private static final String MENSAGEM = "O(s) produto(s) abaixo estão sem estoque\n%s";

    public ProdutoSemEstoqueException(List<String> produtosSemEstoque) {
        this.produtosSemEstoque = produtosSemEstoque;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("Produto sem estoque");
        pb.setDetail(formatarTexto());
        return pb;
    }

    private String formatarTexto() {
        StringJoiner joiner = new StringJoiner("\n");
        for (String produto : produtosSemEstoque) {
            joiner.add(produto);
        }
        return String.format(MENSAGEM, joiner.toString());
    }
}
