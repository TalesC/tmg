package br.com.project.tmg.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionKey {


    private List<Integer> values = new ArrayList<>();
   

    public TransactionKey(ParamsDTO params) {
    	generateKey(params.getUserId(),
    				params.getYear(),
    				params.getMonth());
    }
    
    public TransactionKey(Integer userId, Integer year, Integer month) {
    	generateKey(userId, year, month);
    }
    
    public Integer getRandomValue(Integer index) {
        var size = values.size();

        if (index >= 0) {
            var i = index;
            while(i >= size) {
                i = (i % size == 0) ? (i / size) : 0;
            }
            return values.get(i);
        }else
           return values.get(0);

    }
    
    private void generateKey(Integer userId, Integer year, Integer month) {
    	Integer transactionKey = (userId/100) * year * month;
    	
        for (int i = 0; i < transactionKey.toString().length(); i++){
            var v = Integer.parseInt(transactionKey.toString().substring(i, i +1));
            if(v > 0) values.add(v);
        }
    }

}
