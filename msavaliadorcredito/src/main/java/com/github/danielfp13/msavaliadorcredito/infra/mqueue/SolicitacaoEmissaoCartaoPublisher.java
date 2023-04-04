package com.github.danielfp13.msavaliadorcredito.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danielfp13.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoDeCartao;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private RabbitTemplate rabbitTemplate;
    private Queue queueEmissaoCartoes;


    public void solicitarCartao(DadosSolicitacaoEmissaoDeCartao dados) throws JsonProcessingException {
        var json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(),json);
    }

    private String convertIntoJson(DadosSolicitacaoEmissaoDeCartao dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
       var json =  mapper.writeValueAsString(dados);
       return json;
    }
}
