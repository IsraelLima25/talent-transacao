package br.com.zupacademy.israel.transacao.gerarHistoricoCartao;

public class EstabelecimentoResponse {

    private String id;
    private String nome;
    private String cidade;
    private String endereco;

    public EstabelecimentoResponse(String id, String nome, String cidade, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
