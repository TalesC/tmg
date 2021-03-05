package br.com.project.tmg.service;

import br.com.project.tmg.model.Transaction;
import br.com.project.tmg.model.TransactionKey;
import br.com.project.tmg.model.builder.TransactionBuilder;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

	public boolean validTransactionParameters(Integer userId, Integer year, Integer month) {
        return (userId < 1000 || userId > 100000) ||
              (year > LocalDate.now().getYear() || year < 1962) ||
              (month < 1 || month > 12);
    }
	
    public List<Transaction> generateTransactionListMock(Integer userId, Integer year, Integer month) {

        var transactionListSize = generateTransactioListSize(userId, month);
        var transactions = new ArrayList<Transaction>();

        for (int index = 1; index <= transactionListSize; index++){
            transactions.add(generateMockFromKey(userId, year, month, index));
        }
        return transactions;
    }


    private Integer generateTransactioListSize(Integer userId, Integer mes) {
        var firstUserIdDigit = Integer.parseInt(userId.toString().substring(0,1));
        return mes * firstUserIdDigit;
    }

    private Transaction generateMockFromKey(Integer userId, Integer year, Integer month, Integer index) {
    	
    	var transactionKey = new TransactionKey(userId, year, month);
    	
        return new TransactionBuilder()
                .descricao(transactionKey, index)
                .valor(transactionKey, userId, year, month, index)
                .data(year, month, index)
                .build();
    }


}
