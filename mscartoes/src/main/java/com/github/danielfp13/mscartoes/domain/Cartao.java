package com.github.danielfp13.mscartoes.domain;

import com.github.danielfp13.mscartoes.domain.enums.BaneiraCartao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private BaneiraCartao  bandeira;
    private BigDecimal renda;
    private BigDecimal limite;


}
