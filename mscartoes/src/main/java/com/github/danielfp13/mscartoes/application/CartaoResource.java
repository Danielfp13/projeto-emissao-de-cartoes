package com.github.danielfp13.mscartoes.application;

import com.github.danielfp13.mscartoes.application.representation.CartaoSaveRequest;
import com.github.danielfp13.mscartoes.application.representation.CartoesPorClienteResponse;
import com.github.danielfp13.mscartoes.domain.Cartao;
import com.github.danielfp13.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/cartoes")
public class CartaoResource {

    private CartaoService cartaoService;
    private ClienteCartaoService clienteCartaoService;

    @GetMapping("/ok")
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Cartao> cadastro(@RequestBody CartaoSaveRequest request){
        Cartao cartao = request.toModel();
        cartao = cartaoService.save(cartao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(cartao);
    }

    @GetMapping
    public ResponseEntity<List<Cartao>>getCartoesRendaAteh(@RequestParam Long renda){
        return ResponseEntity.ok(cartaoService.getCartoesRendaMenorIgual(renda));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>>getCartoesByClientes(@RequestParam String cpf){
        List<ClienteCartao> list =  clienteCartaoService.findCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = list.stream().map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());        
        return ResponseEntity.ok(resultList);
    }
}







