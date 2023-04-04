package com.github.danielfp13.mscartoes.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosSolicitacaoEmissaoDeCartao {
    private Long idCartao;
    private String cfp;
    private String endereco;
    private BigDecimal limiteLiberado;
}
