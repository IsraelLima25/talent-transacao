package br.com.zupacademy.israel.transacao.gerarHistoricoCartao;

import br.com.zupacademy.israel.transacao.consumirTransacoes.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

}
