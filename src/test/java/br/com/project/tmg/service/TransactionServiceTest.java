package br.com.project.tmg.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.project.tmg.model.Transaction;

@SpringBootTest
class TransactionServiceTest {

    private List<Transaction> transacoes;

    @Autowired
    private TransactionService service;

    @BeforeEach
    void init() {
        this.transacoes = service.generate(1000, 2019, 12);
    }

    @Test
    void deveRetornarUmaListaDeTransacoes() {
        var lista = this.transacoes;

        Assertions.assertNotNull(lista);
        Assertions.assertTrue(!lista.isEmpty());
        Assertions.assertTrue(lista.size() == 12);
        lista.forEach(transacao -> {
        	Assertions.assertTrue(transacao instanceof  Transaction);
        });
    }

    @Test
    void deveRetornarMesmaListaParaMesmaInputDeDados() {
        var transacoes2 = service.generate(1000, 2019, 12);

        Assertions.assertEquals(this.transacoes, transacoes2);
    }

    @Test
    void deveRetornarDescricaoValidaParaCadaTransacao() {
        this.transacoes.stream().forEach( transacao -> {
            var descricao= transacao.getDescription();

            Assertions.assertNotNull(descricao);
            Assertions.assertTrue(descricao instanceof String);
            Assertions.assertTrue(descricao.length() > 10 && descricao.length() < 60);
            //faltou verificar se a descrição é legivel
        });
    }

    @Test
    void deveRetornarValorValidoParaCadaTransacao() {
        this.transacoes.stream().forEach( transacao -> {
            var valor = transacao.getValue();

            Assertions.assertNotNull(valor);
            Assertions.assertTrue(valor instanceof Integer);
            Assertions.assertTrue(valor > -9999999 && valor < 9999999);
        });
    }

    @Test
    void deveRetornarDataValidaParacadTransacao() {

        var datas = getDatasResultMatcher();

        this.transacoes.forEach(transacao -> {
            var data = transacao.getDate();
            Timestamp timestamp = new Timestamp(data);

            Assertions.assertNotNull(data);
            Assertions.assertNotNull(timestamp);
            Assertions.assertTrue(data instanceof Long);
            Assertions.assertTrue(datas.contains(timestamp.toString()));
        });
    }

    private List<String> getDatasResultMatcher() {
        return Arrays.asList(
                "2019-12-01 00:00:00.0",
                "2019-12-02 00:00:00.0",
                "2019-12-03 00:00:00.0",
                "2019-12-04 00:00:00.0",
                "2019-12-05 00:00:00.0",
                "2019-12-06 00:00:00.0",
                "2019-12-07 00:00:00.0",
                "2019-12-08 00:00:00.0",
                "2019-12-09 00:00:00.0",
                "2019-12-10 00:00:00.0",
                "2019-12-11 00:00:00.0",
                "2019-12-12 00:00:00.0"
        );
    }


}