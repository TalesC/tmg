package br.com.project.tmg.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tmg.service.TransacaoService;

@RestController
@CrossOrigin
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public ResponseEntity generateTransaction(@PathVariable("id") Integer userId,
                                              @PathVariable("ano") Integer ano,
                                              @PathVariable("mes") Integer mes) {

        if(isInvalid(userId, ano, mes))
            return ResponseEntity.badRequest().body(genErrorMessage(userId, ano, mes));

        var transacoes = transacaoService.generateMockList(userId, ano, mes);
        return ResponseEntity.ok().body(transacoes);
    }

    private boolean isInvalid(Integer userId, Integer ano, Integer mes) {
        return (userId < 1000 || userId > 100000) ||
              (ano > LocalDate.now().getYear() || ano < 1962) ||
              (mes < 1 || mes > 12);
    }

    private String genErrorMessage(Integer userId, Integer ano, Integer mes) {
        return new StringBuilder()
                .append("Erro!! Parametros invalidos:")
                .append((userId < 1000 || userId > 100000) ? "\n Id do usurio precisa ser maior que 1000 ou menor que 100000.":"")
                .append((ano > LocalDate.now().getYear()  || ano < 1962) ? "\nO ano não pode ser maior que o ano atual.":"")
                .append((mes < 1 || mes > 12) ? "\n O mes invalido.":"")
                .toString();
    }

}
