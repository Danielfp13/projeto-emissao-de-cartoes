package com.github.danielfp13.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosAvaliacao {

    private String cpf;
    private Long renda;
}
