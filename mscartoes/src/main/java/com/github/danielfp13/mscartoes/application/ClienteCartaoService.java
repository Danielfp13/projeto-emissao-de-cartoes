package com.github.danielfp13.mscartoes.application;

import com.github.danielfp13.mscartoes.domain.Cartao;
import com.github.danielfp13.mscartoes.domain.ClienteCartao;
import com.github.danielfp13.mscartoes.infra.repository.CartaoReository;
import com.github.danielfp13.mscartoes.infra.repository.ClienteCartaoReository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteCartaoService {

    private ClienteCartaoReository clienteCartaoReository;


    public List<ClienteCartao> findCartoesByCpf(String cpf){
        return clienteCartaoReository.findByCpf(cpf);
    }
}
