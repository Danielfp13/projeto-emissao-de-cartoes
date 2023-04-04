
package com.github.danielfp13.msavaliadorcredito.application;

import com.github.danielfp13.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.github.danielfp13.msavaliadorcredito.application.exception.ErroComunicacaoMicroservceiException;
import com.github.danielfp13.msavaliadorcredito.application.exception.ErroSolicitacaoException;
import com.github.danielfp13.msavaliadorcredito.domain.model.*;
import com.github.danielfp13.msavaliadorcredito.infra.clients.CartoesResourseClient;
import com.github.danielfp13.msavaliadorcredito.infra.clients.ClienteResourseClient;
import com.github.danielfp13.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvaliadorCreditoService {

    private ClienteResourseClient clienteResourseClient;
    private CartoesResourseClient cartoesResourseClient;
    private SolicitacaoEmissaoCartaoPublisher publisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservceiException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourseClient.findCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesClienteResponse = cartoesResourseClient.getCartoesByClientes(cpf);

            return SituacaoCliente.builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesClienteResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservceiException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservceiException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourseClient.findCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourseClient.getCartoesRendaAteh(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> cartoesAprovados = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limite = cartao.getLimite();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limite);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(cartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservceiException(e.getMessage(), status);
        }

    }

    public ProtocoloSolicitacaoCartao solicitarEmisaoCartao(DadosSolicitacaoEmissaoDeCartao dados){
        try {
            publisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception ex){
            throw new ErroSolicitacaoException(ex.getMessage());
        }
    }
}