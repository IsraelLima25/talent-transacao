package br.com.zupacademy.israel.transacao.consumirTransacoes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoConsumer {

    private String id;
    private BigDecimal valor;
    @JsonProperty("estabelecimento")
    private EstabelecimentoConsumer estabelecimentoConsumer;
    @JsonProperty("cartao")
    private CartaoConsumer cartaoConsumer;
    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoConsumer getEstabelecimentoConsumer() {
        return estabelecimentoConsumer;
    }

    public CartaoConsumer getCartaoConsumer() {
        return cartaoConsumer;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel() {
        return new Transacao(id,valor,estabelecimentoConsumer.toModel(),cartaoConsumer.toModel(),efetivadaEm);
    }
}
