package br.com.project.tmg.service;

import br.com.project.tmg.dto.ParamsDTO;
import br.com.project.tmg.model.Transaction;
import br.com.project.tmg.model.TransactionKey;
import br.com.project.tmg.model.builder.TransactionBuilder;
import br.com.project.tmg.model.builder.TransactionDescriptionBuilder;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

	public boolean validParameters(Integer userId, Integer year, Integer month) {
        return (userId >= 1000 && userId <= 100000) &&
               (year <= LocalDate.now().getYear() && year >= 1962) &&
               (month >= 1 && month <= 12);
    }
	
    public List<Transaction> generate(Integer userId, Integer year, Integer month) {

        var transactionListSize = generateListSize(userId, month);
        var transactions = new ArrayList<Transaction>();

        for (int transactionIndex = 1; transactionIndex <= transactionListSize; transactionIndex++){
        	var params = new ParamsDTO(userId, year, month, transactionIndex); 
            transactions.add(generateTransactionFromKey(params));
        }
        return transactions;
    }

    private Integer generateListSize(Integer userId, Integer month) {
        var userIdFirstDigit = Integer.parseInt(userId.toString().substring(0,1));
        return month * userIdFirstDigit;
    }

    private Transaction generateTransactionFromKey(ParamsDTO params) {
    	
    	var transactionKey = new TransactionKey(params);
    	
        return new TransactionBuilder()
                .description(generateDescription(transactionKey, params))
                .value(transactionKey, params)
                .date(params)
                .build();
    }
    
    private String generateDescription(TransactionKey key , ParamsDTO params) {
    	return new TransactionDescriptionBuilder()
        		.description(key, params.getTransactionIndex())
        		.build();
    }


}
