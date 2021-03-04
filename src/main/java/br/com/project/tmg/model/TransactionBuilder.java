package br.com.project.tmg.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import br.com.project.tmg.utils.RandomValuesUtils;

public class TransactionBuilder {

    private String descricao;
    private Integer valor;
    private Long data;


    private Integer userId, ano, mes, index;

    private RandomValuesUtils random;

    public TransactionBuilder(Integer userId, Integer ano, Integer mes, Integer index) {
        this.userId = userId;
        this.ano = ano;
        this.mes = mes;
        this.index = index;

        var value = (userId/100) * ano * mes;
        this.random = new RandomValuesUtils(value);
    }

    public TransactionBuilder descricao() {
        var desc = new StringBuffer();
        var words = random.getOneValue(index);

        for(int i = 0; i <= words; i++){
            var syllables = random.getOneValue(i);

            var futureSize = desc.length() + (2 * syllables) + 1;
            if (futureSize > 51) break;

            desc.append(generateRandomWord(syllables))
                 .append(" ");
        }
        this.descricao = desc.toString().trim();

        return this;
    }

    public TransactionBuilder valor() {
        var multiplier = random.getOneValue(index);
        var signal = Math.pow(-1, multiplier);

        Double valor = (multiplier * userId / mes) * signal;
        this.valor = valor.intValue();

        return this;
    }

    public TransactionBuilder data(){
        var data = LocalDate.of(ano, mes, getDay(ano, mes, index));
        Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());

        this.data = timestamp.getTime();

        return this;
    }

    public Transaction build() {
        return new Transaction(this.descricao, this.data, this.valor);
    }

    private String generateRandomWord(int numberOfSyllables) {
        var consonants = "bcdfghjklmnpqrstvhwz";
        var vowels = "aeiou";
        var word = new StringBuilder();

        for (int i = 0; i <= numberOfSyllables; i++) {
            var consonant =  getLetter(consonants, i);
            var vowel = getLetter(vowels, i);

            word.append(consonant).append(vowel);
        }
        return word.toString();
    }

    private char getLetter(String letters, Integer number){
        var random = this.random.getOneValue(number);
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
