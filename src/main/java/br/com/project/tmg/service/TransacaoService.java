package br.com.project.tmg.service;

import br.com.project.tmg.model.Transacao;
import br.com.project.tmg.model.TransacaoBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    public List<Transacao> generateMockList(Integer userId, Integer ano, Integer mes) {

        var lenth = getLenghList(userId, mes);
        var trasacoes = new ArrayList<Transacao>();

        for (int index = 1; index<= lenth; index++){
            trasacoes.add(genTransacapMock(userId, ano, mes, index));
        }
        return trasacoes;
    }


    private Integer getLenghList(Integer userId, Integer mes) {
        var multiplicador = Integer.parseInt(userId.toString().substring(0,1));
        return mes * multiplicador;
    }

    private Transacao genTransacapMock(Integer userId, Integer ano, Integer mes, Integer index) {
        return new TransacaoBuilder(userId, ano, mes, index)
                .descricao()
                .valor()
                .data()
                .build();
    }


}
