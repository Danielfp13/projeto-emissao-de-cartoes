package com.github.danielfp13.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danielfp13.mscartoes.domain.Cartao;
import com.github.danielfp13.mscartoes.domain.ClienteCartao;
import com.github.danielfp13.mscartoes.domain.DadosSolicitacaoEmissaoDeCartao;
import com.github.danielfp13.mscartoes.infra.repository.CartaoRepository;
import com.github.danielfp13.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EmissorCartaoSubscriber {

    private CartaoRepository cartaoRepository;
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitaçãoDeEmissao(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoDeCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoDeCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCfp());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartao = clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            log.error("Error ao receber solicitação de emissão de cartão: {}", e.getMessage());
        }
    }
}