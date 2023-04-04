package com.github.danielfp13.msavaliadorcredito.domain.model;

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
