package com.github.danielfp13.mscartoes.application;

import com.github.danielfp13.mscartoes.domain.ClienteCartao;
import com.github.danielfp13.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteCartaoService {

    private ClienteCartaoRepository clienteCartaoReository;


    public List<ClienteCartao> findCartoesByCpf(String cpf){
        return clienteCartaoReository.findByCpf(cpf);
    }
}
