package br.com.zupacademy.israel.transacao.gerarHistoricoCartao;

import br.com.zupacademy.israel.transacao.consumirTransacoes.Cartao;
import br.com.zupacademy.israel.transacao.consumirTransacoes.Transacao;
import br.com.zupacademy.israel.transacao.consumirTransacoes.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historicoCartoes")
public class GerarHistoricoCartaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping("/{idCartao}")
    public ResponseEntity<List<TransacaoResponse>> gerarTop10HistoricoTransacoesCartao(@PathVariable String idCartao) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if (!possivelCartao.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idTransacaoSistema").descending());
        List<Transacao> pageList = transacaoRepository.findByCartaoIdCartao(idCartao, pageRequest).getContent();
        List<TransacaoResponse> transacoesResponse = pageList
                .stream().map(TransacaoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(transacoesResponse);
    }
}
