package br.com.project.tmg.model.builder;

import java.sql.Timestamp;
import java.time.LocalDate;

import br.com.project.tmg.model.TransactionKey;
import br.com.project.tmg.model.Transaction;

public class TransactionBuilder {

    private String description;
    private Integer value;
    private Long date;


    public TransactionBuilder() {
       super(); 
    }

    public TransactionBuilder description(TransactionKey key, Integer index) {
        
        this.description = new TransactionDescriptionBuilder()
        		.description(key, index)
        		.build();

        return this;
    }

    public TransactionBuilder value(TransactionKey key, Integer userId, Integer year, Integer month, Integer index) {
        var multiplier = key.getRandomValue(index);
        var signal = Math.pow(-1, multiplier);

        Double valor = (multiplier * userId / month) * signal;
        this.value = valor.intValue();

        return this;
    }

    public TransactionBuilder date(Integer year, Integer month, Integer index){
        var data = LocalDate.of(year, month, getDay(year, month, index));
        Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());

        this.date = timestamp.getTime();

        return this;
    }

    public Transaction build() {
        return new Transaction(this.description, this.date, this.value);
    }


    private  int getDay(int ano, int mes, int index) {
        var firstDay= LocalDate.of(ano, mes, 1);
        var maxDayOfMonth = firstDay.withDayOfMonth(firstDay.getMonth().length(firstDay.isLeapYear())).getDayOfMonth();
        var day = index;

        while(day > maxDayOfMonth) {
            day = day / maxDayOfMonth;
        }

        return day;
    }


}
