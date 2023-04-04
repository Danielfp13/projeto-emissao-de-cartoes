package com.github.danielfp13.mscartoes.application;

import com.github.danielfp13.mscartoes.domain.Cartao;
import com.github.danielfp13.mscartoes.infra.repository.CartaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CartaoService {

    private CartaoRepository cartaoReository;

    @Transactional
    public Cartao save(Cartao cartao){
        return cartaoReository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return cartaoReository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
