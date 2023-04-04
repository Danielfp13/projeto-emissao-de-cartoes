package com.github.danielfp13.msavaliadorcredito.application.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservceiException extends Exception{
    @Getter
    private Integer status;

    public ErroComunicacaoMicroservceiException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
