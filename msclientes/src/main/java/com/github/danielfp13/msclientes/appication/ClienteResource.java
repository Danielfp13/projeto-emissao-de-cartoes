package com.github.danielfp13.msclientes.appication;

import com.github.danielfp13.msclientes.appication.representation.ClienteSaveRequest;
import com.github.danielfp13.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("clientes")
@Slf4j
public class ClienteResource {

    private ClienteService clienteService;

    @GetMapping("/ok")
    public String status(){
        log.info("obtendo status do microservices declarantes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Cliente>save(@RequestBody ClienteSaveRequest request){
        var cliente = request.toModel();
        cliente = clienteService.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                query("cpf={cpf}").buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity findCliente(@RequestParam String cpf){
        var cliente = clienteService.getByCpf(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
