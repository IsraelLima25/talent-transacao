package br.com.zupacademy.israel.transacao.consumirTransacoes;

import br.com.zupacademy.israel.transacao.gerarHistoricoCartao.EstabelecimentoResponse;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Estabelecimento {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID idEstabelecimento;
    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.idEstabelecimento = UUID.randomUUID();
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public EstabelecimentoResponse mapEstabelecimentoResponse() {
        return new EstabelecimentoResponse(idEstabelecimento.toString(), nome, cidade, endereco);
    }

}
