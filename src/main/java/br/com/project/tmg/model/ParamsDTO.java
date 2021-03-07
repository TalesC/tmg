package br.com.project.tmg.model;

public class ParamsDTO {
	
	private Integer userId, year, month, transactionIndex;

	public ParamsDTO(Integer userId, Integer year, Integer month, Integer transactionIndex) {
		super();
		this.userId = userId;
		this.year = year;
		this.month = month;
		this.transactionIndex = transactionIndex;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getTransactionIndex() {
		return transactionIndex;
	}

}
