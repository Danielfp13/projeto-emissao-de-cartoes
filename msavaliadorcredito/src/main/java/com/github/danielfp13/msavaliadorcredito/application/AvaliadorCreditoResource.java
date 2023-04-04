package com.github.danielfp13.msavaliadorcredito.application;

import com.github.danielfp13.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.github.danielfp13.msavaliadorcredito.application.exception.ErroComunicacaoMicroservceiException;
import com.github.danielfp13.msavaliadorcredito.application.exception.ErroSolicitacaoException;
import com.github.danielfp13.msavaliadorcredito.domain.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoResource {

    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping("/ok")
    public String status() {
        return "ok";
    }

    @GetMapping("/situacao-cliente")
    public ResponseEntity consultaSituacaoCliente(@RequestParam String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservceiException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }


    @PostMapping()
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) {
        try {
      RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService
              .realizarAvaliacao(dadosAvaliacao.getCpf(),dadosAvaliacao.getRenda());
      return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservceiException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("/solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoDeCartao dados){
        try {
            ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarEmisaoCartao(dados);
            return ResponseEntity.ok(protocolo);
        }catch (ErroSolicitacaoException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
