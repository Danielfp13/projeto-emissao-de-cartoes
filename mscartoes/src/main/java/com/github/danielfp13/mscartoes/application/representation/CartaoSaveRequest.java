package com.github.danielfp13.mscartoes.application.representation;

import com.github.danielfp13.mscartoes.domain.Cartao;
import com.github.danielfp13.mscartoes.domain.enums.BaneiraCartao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoSaveRequest {

    private String nome;
    private BaneiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(null,nome,bandeira, renda,limite);
    }
}
