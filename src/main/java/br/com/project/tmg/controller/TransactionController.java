package br.com.project.tmg.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tmg.service.TransactionService;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transacaoService;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public ResponseEntity<Object> generateTransaction(@PathVariable("id") Integer userId,
                                              @PathVariable("ano") Integer year,
                                              @PathVariable("mes") Integer month) {

        if(transacaoService.validTransactionParameters(userId, year, month))
            return ResponseEntity.badRequest().body("Erro!! Parametros invalidos.");

        var transacoes = transacaoService.generateMockList(userId, year, month);
        return ResponseEntity.ok().body(transacoes);
    }

    

}
