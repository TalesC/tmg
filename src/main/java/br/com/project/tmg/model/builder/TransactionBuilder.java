package br.com.project.tmg.model.builder;

import java.sql.Timestamp;
import java.time.LocalDate;

import br.com.project.tmg.model.TransactionKey;
import br.com.project.tmg.dto.ParamsDTO;
import br.com.project.tmg.model.Transaction;

public class TransactionBuilder {

    private String description;
    private Integer value;
    private Long date;


    public TransactionBuilder() {
       super(); 
    }

    public TransactionBuilder description(String description) {
        this.description = description; 
        return this;
    }

    public TransactionBuilder value(TransactionKey key, ParamsDTO params) {
        var multiplier = key.getRandomValue(params.getTransactionIndex());
        var signal = Math.pow(-1, multiplier);

        Double valor = (multiplier * params.getUserId() / params.getMonth()) * signal;
        this.value = valor.intValue();

        return this;
    }

    public TransactionBuilder date(ParamsDTO params){
        var data = LocalDate.of(params.getYear(), params.getMonth(), getDay(params));
        Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());

        this.date = timestamp.getTime();

        return this;
    }

    public Transaction build() {
        return new Transaction(this.description, this.date, this.value);
    }


    private  int getDay(ParamsDTO params) {
        var firstDay= LocalDate.of(params.getYear(), params.getMonth(), 1);
        var maxDayOfMonth = firstDay.withDayOfMonth(firstDay.getMonth().length(firstDay.isLeapYear())).getDayOfMonth();
        var day = params.getTransactionIndex();

        while(day > maxDayOfMonth) {
            day = day / maxDayOfMonth;
        }

        return day;
    }


}
