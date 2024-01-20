package br.com.zupacademy.israel.transacao.consumirTransacoes;

import br.com.zupacademy.israel.transacao.gerarHistoricoCartao.CartaoResponse;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    private String idCartao;
    private String email;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String idCartao, String email) {
        this.idCartao = idCartao;
        this.email = email;
    }

    public CartaoResponse mapCartaoResponse() {
        return new CartaoResponse(idCartao, email);
    }

}
