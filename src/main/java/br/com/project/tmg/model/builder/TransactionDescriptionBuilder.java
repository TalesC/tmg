package br.com.project.tmg.model.builder;

import br.com.project.tmg.model.TransactionKey;

public class TransactionDescriptionBuilder {
	
	private String description;
	
	public TransactionDescriptionBuilder description(TransactionKey key, Integer index) {
		var description = new StringBuffer();
        var numberOfWords = key.getRandomValue(index);

        for(int wordIndex = 0; wordIndex <= numberOfWords; wordIndex++){
            var numberOfSyllables = key.getRandomValue(wordIndex);

            var futureSize = description.length() + (2 * numberOfSyllables) + 1;
            if (futureSize > 51) break;

            description.append(generateRandomWord(key, numberOfSyllables))
                 .append(" ");
        }
		
        this.description = description.toString().trim();
		
        return this;
	}
	
	public String build() {
		return this.description;
	}
	
    private String generateRandomWord(TransactionKey key, Integer numberOfSyllables) {
        var consonants = "bcdfghjklmnpqrstvhwz";
        var vowels = "aeiou";
        var word = new StringBuilder();

        for (int i = 0; i <= numberOfSyllables; i++) {
        	var ramdonLetterIndex = key.getRandomValue(i);
        	
            var consonant =  getLetter(consonants, ramdonLetterIndex);
            var vowel = getLetter(vowels, ramdonLetterIndex);

            word.append(consonant).append(vowel);
        }
        return word.toString();
    }

    private char getLetter(String letters, Integer lettersIndex){
        var lettersSize = letters.length();

        while(lettersIndex >= lettersSize) {
        	lettersIndex = (lettersIndex % lettersSize) == 0 ? (lettersIndex/lettersSize) : 0;
        }

        return letters.charAt(lettersIndex);
    }

}
