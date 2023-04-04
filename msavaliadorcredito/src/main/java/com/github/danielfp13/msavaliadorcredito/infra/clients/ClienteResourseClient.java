package com.github.danielfp13.msavaliadorcredito.infra.clients;

import com.github.danielfp13.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="msclientes",  path="/clientes")
public interface ClienteResourseClient {

    @GetMapping
    ResponseEntity<DadosCliente> findCliente(@RequestParam String cpf);

}
