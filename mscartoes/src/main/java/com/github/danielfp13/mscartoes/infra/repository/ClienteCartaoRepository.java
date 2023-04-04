package com.github.danielfp13.mscartoes.infra.repository;

import com.github.danielfp13.mscartoes.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    public List<ClienteCartao>findByCpf(String cpf);
}
