package com.github.danielfp13.msclientes.appication.representation;

import com.github.danielfp13.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
       return new Cliente(cpf, nome, idade) ;
    }
}
