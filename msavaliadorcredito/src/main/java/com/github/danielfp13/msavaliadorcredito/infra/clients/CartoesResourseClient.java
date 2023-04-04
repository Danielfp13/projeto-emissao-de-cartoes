package com.github.danielfp13.msavaliadorcredito.infra.clients;

import com.github.danielfp13.msavaliadorcredito.domain.model.Cartao;
import com.github.danielfp13.msavaliadorcredito.domain.model.CartaoCliente;
import com.github.danielfp13.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="mscartoes",  path="/cartoes")
public interface CartoesResourseClient {

    @GetMapping
    ResponseEntity<List<CartaoCliente>>getCartoesByClientes(@RequestParam String cpf);

    @GetMapping
    public ResponseEntity<List<Cartao>>getCartoesRendaAteh(@RequestParam Long renda);

}
