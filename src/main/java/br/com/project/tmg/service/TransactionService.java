package br.com.project.tmg.service;

import java.util.List;

import br.com.project.tmg.model.Transaction;

public interface TransactionService {
	
	public Boolean validParameters(Integer userId, Integer year, Integer month) ;
	public List<Transaction> generate(Integer userId, Integer year, Integer month);
	
}
