package com.github.danielfp13.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
