package br.com.zupacademy.israel.transacao;

import br.com.zupacademy.israel.transacao.consumirTransacoes.Cartao;
import br.com.zupacademy.israel.transacao.consumirTransacoes.Estabelecimento;
import br.com.zupacademy.israel.transacao.consumirTransacoes.Transacao;
import br.com.zupacademy.israel.transacao.consumirTransacoes.TransacaoRepository;
import br.com.zupacademy.israel.transacao.gerarHistoricoCartao.CartaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class GerarHistoricoCartaoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CartaoRepository cartaoRepository;
    @MockBean
    TransacaoRepository transacaoRepository;
    List<Transacao> transacoes;

    @BeforeEach
    public void setup() {
        transacoes = Arrays.asList(new Transacao("1245aax", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("12451asaz", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("124545a65s6a", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("124554as85", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("124asz5azq9", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("1245548weasz9", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("1245az5487q", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("1245asaszq25", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("1245394659", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("124532az2544", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()),
                new Transacao("1245asq3697q", new BigDecimal("5.56"), new Estabelecimento("nome", "cidade", "endereco"),
                        new Cartao("1ab56c", "user@gmail.com"), LocalDateTime.now()));
    }

    @Test
    void deveRetornar10UltimasTransacoesCartao() throws Exception {
        Optional<Cartao> cartaoOptional = Optional.of(new Cartao("1ab56c", "user@gmail.com"));
        Mockito.when(cartaoRepository.findById("1ab56c")).thenReturn(cartaoOptional);
        Collections.reverse(transacoes);
        List<Transacao> ultimas10TransacoesMock = transacoes.subList(0, 10);
        PageImpl page = new PageImpl(ultimas10TransacoesMock);
        Mockito.when(transacaoRepository.findByCartaoIdCartao("1ab56c", getPageRequest())).thenReturn(page);
        mockMvc.perform(get("/historicoCartoes/1ab56c")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_admin")))
        ).andExpect(status().isOk());

        Assertions.assertTrue(ultimas10TransacoesMock.get(0).getIdTransacao().equals("1245asq3697q"));
        Assertions.assertTrue(ultimas10TransacoesMock.get(9).getIdTransacao().equals("12451asaz"));
    }

    @Test
    void deveRetornarNotFoundQuandoTransacoesCartaoInexistente() throws Exception {
        String idCartao = "12ac658c9";
        Mockito.when(cartaoRepository.findById(idCartao)).thenReturn(Optional.empty());
        mockMvc.perform(get("/historicoCartoes/" + idCartao)
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_admin")))
        ).andExpect(status().isNotFound());
    }

    PageRequest getPageRequest() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idTransacaoSistema").descending());
        return pageRequest;
    }
}
