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

	public boolean validParameters(Integer userId, Integer year, Integer month) {
        return (userId < 1000 || userId > 100000) ||
              (year > LocalDate.now().getYear() || year < 1962) ||
              (month < 1 || month > 12);
    }
	
    public List<Transaction> generateTransactionsMock(Integer userId, Integer year, Integer month) {

        var transactionListSize = generateTransactioListSize(userId, month);
        var transactions = new ArrayList<Transaction>();

        for (int transactioIndex = 1; transactioIndex <= transactionListSize; transactioIndex++){
            transactions.add(generateTransactionMockFromKey(userId, year, month, transactioIndex));
        }
        return transactions;
    }


    private Integer generateTransactioListSize(Integer userId, Integer mes) {
        var firstUserIdDigit = Integer.parseInt(userId.toString().substring(0,1));
        return mes * firstUserIdDigit;
    }

    private Transaction generateTransactionMockFromKey(Integer userId, Integer year, Integer month, Integer index) {
    	
    	var transactionKey = new TransactionKey(userId, year, month);
    	
        return new TransactionBuilder()
                .description(transactionKey, index)
                .value(transactionKey, userId, year, month, index)
                .date(year, month, index)
                .build();
    }


}
