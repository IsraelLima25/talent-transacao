package br.com.zupacademy.israel.transacao.consumirTransacoes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByCartaoIdCartao(String idCartao, Pageable pageable);
}
