package br.com.zupacademy.israel.transacao.consumirTransacoes;

public class CartaoConsumer {

    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(id, email);
    }

    @Override
    public String toString() {
        return "CartaoConsumer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
