package com.github.danielfp13.msavaliadorcredito.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;
}
