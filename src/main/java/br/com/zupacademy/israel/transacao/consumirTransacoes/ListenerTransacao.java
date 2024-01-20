package br.com.zupacademy.israel.transacao.consumirTransacoes;

import br.com.zupacademy.israel.transacao.gerarHistoricoCartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTransacao {

    private final Logger logger = LoggerFactory.getLogger(ListenerTransacao.class);

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void listener(TransacaoConsumer transacaoConsumer) {
        logger.info("Consumindo transação "+transacaoConsumer.getId());
        Transacao transacao = transacaoConsumer.toModel();
        Cartao cartaoSalvo = salvarCartao(transacao.getCartao());
        Estabelecimento estabelecimentoSalvo = salvarEstabelecimento(transacao.getEstabelecimento());
        transacao.adicionarCartao(cartaoSalvo);
        transacao.adicionarEstabelecimento(estabelecimentoSalvo);
        salvarTransacao(transacao);
        logger.info("Transação "+transacaoConsumer.getId()+" salva.");
    }

    private Cartao salvarCartao(Cartao cartao) {
        Cartao cartaoSalvo = cartaoRepository.save(cartao);
        return cartaoSalvo;
    }

    private Estabelecimento salvarEstabelecimento(Estabelecimento estabelecimento){
        Estabelecimento estabelecimentoSalvo= estabelecimentoRepository.save(estabelecimento);
        return estabelecimentoSalvo;
    }

    private void salvarTransacao(Transacao transacao){
        transacaoRepository.save(transacao);
    }
}
