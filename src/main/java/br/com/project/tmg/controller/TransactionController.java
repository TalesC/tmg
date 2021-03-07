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
    private TransactionService transactionService;

    @GetMapping("/{id}/transactions/{year}/{month}")
    public ResponseEntity<Object> generate(@PathVariable("id") Integer userId,
		                                              @PathVariable("year") Integer year,
		                                              @PathVariable("month") Integer month) {

        if(!transactionService.validParameters(userId, year, month))
            return ResponseEntity.badRequest().body("Erro!! Parametros invalidos.");

        var transactions = transactionService.generate(userId, year, month);
        return ResponseEntity.ok().body(transactions);
    }

    

}
