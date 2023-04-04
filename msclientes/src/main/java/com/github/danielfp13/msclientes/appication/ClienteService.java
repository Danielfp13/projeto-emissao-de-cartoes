package com.github.danielfp13.msclientes.appication;

import com.github.danielfp13.msclientes.domain.Cliente;
import com.github.danielfp13.msclientes.infra.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente clinete) {
        return clienteRepository.save(clinete);
    }

    public Optional<Cliente> getByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
}
