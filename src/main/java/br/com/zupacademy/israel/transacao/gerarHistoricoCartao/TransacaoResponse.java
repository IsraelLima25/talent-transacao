package br.com.zupacademy.israel.transacao.gerarHistoricoCartao;

import br.com.zupacademy.israel.transacao.consumirTransacoes.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimentoResponse;
    private CartaoResponse cartaoResponse;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao transacao) {
        this.id = transacao.getIdTransacao();
        this.valor = transacao.getValor();
        this.estabelecimentoResponse = transacao.getEstabelecimento().mapEstabelecimentoResponse();
        this.cartaoResponse = transacao.getCartao().mapCartaoResponse();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimentoResponse() {
        return estabelecimentoResponse;
    }

    public CartaoResponse getCartaoResponse() {
        return cartaoResponse;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
