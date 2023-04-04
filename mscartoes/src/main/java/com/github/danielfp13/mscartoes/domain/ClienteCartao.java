package com.github.danielfp13.mscartoes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

    private BigDecimal limite;


}
