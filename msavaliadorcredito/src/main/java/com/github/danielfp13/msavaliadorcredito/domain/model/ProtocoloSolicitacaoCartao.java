package com.github.danielfp13.msavaliadorcredito.domain.model;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProtocoloSolicitacaoCartao {

    private String protocolo;

}
