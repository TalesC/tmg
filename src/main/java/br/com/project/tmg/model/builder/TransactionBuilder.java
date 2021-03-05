package br.com.project.tmg.model.builder;

import java.sql.Timestamp;
import java.time.LocalDate;

import br.com.project.tmg.model.TransactionKey;
import br.com.project.tmg.model.Transaction;

public class TransactionBuilder {

    private String descricao;
    private Integer valor;
    private Long data;


    public TransactionBuilder() {
       super(); 
    }

    public TransactionBuilder descricao(TransactionKey key, Integer index) {
        var desc = new StringBuffer();
        var words = key.getRandomValue(index);

        for(int i = 0; i <= words; i++){
            var syllables = key.getRandomValue(i);

            var futureSize = desc.length() + (2 * syllables) + 1;
            if (futureSize > 51) break;

            desc.append(generateRandomWord(key,syllables))
                 .append(" ");
        }
        this.descricao = desc.toString().trim();

        return this;
    }

    public TransactionBuilder valor(TransactionKey key, Integer userId, Integer year, Integer month, Integer index) {
        var multiplier = key.getRandomValue(index);
        var signal = Math.pow(-1, multiplier);

        Double valor = (multiplier * userId / month) * signal;
        this.valor = valor.intValue();

        return this;
    }

    public TransactionBuilder data(Integer year, Integer month, Integer index){
        var data = LocalDate.of(year, month, getDay(year, month, index));
        Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());

        this.data = timestamp.getTime();

        return this;
    }

    public Transaction build() {
        return new Transaction(this.descricao, this.data, this.valor);
    }

    
    private String generateRandomWord(TransactionKey key, Integer numberOfSyllables) {
        var consonants = "bcdfghjklmnpqrstvhwz";
        var vowels = "aeiou";
        var word = new StringBuilder();

        for (int i = 0; i <= numberOfSyllables; i++) {
            var consonant =  getLetter(key, consonants, i);
            var vowel = getLetter(key, vowels, i);

            word.append(consonant).append(vowel);
        }
        return word.toString();
    }

    private char getLetter(TransactionKey key, String letters, Integer number){
        var random = key.getRandomValue(number);
        var size = letters.length();

        while(random >= size) {
            random = (random % size) == 0 ? (random/size) : 0;
        }

        return letters.charAt(random);
    }

    private  int getDay(int ano, int mes, int index) {
        var dia1= LocalDate.of(ano, mes, 1);
        var diaMax = dia1.withDayOfMonth(dia1.getMonth().length(dia1.isLeapYear())).getDayOfMonth();
        var dia = index;

        while(dia > diaMax) {
            dia = dia / diaMax;
        }

        return dia;
    }


}
