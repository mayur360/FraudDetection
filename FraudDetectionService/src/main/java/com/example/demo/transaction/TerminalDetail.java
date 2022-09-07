package com.example.demo.transaction;

public enum TerminalDetail {
	
	TERMINAL_ID_1 ("U478910", 1000000.0),
	TERMINAL_ID_2 ("U578910", 1000000.0),
	TERMINAL_ID_3 ("U678910", 1000000.0),
	TERMINAL_ID_4 ("U878910", 1000000.0),
	TERMINAL_ID_5 ("U978910", 1000000.0),
	TERMINAL_ID_6 ("SU478910", 2000000.0),
	TERMINAL_ID_7 ("SU578910", 2000000.0),
	TERMINAL_ID_8 ("SU678910", 2000000.0),
	TERMINAL_ID_9 ("SU778910", 2000000.0),
	TERMINAL_ID_10("SU878910", 2000000.0);
	
	private double transactionLimit;
	private String terminalId;
	
	TerminalDetail(String terminalId, double transactionLimit) {
		this.terminalId = terminalId;
		this.transactionLimit = transactionLimit;
	}

	public double getTransactionLimit() {
		return transactionLimit;
	}

	public String getTerminalId() {
		return terminalId;
	}
	
	
}
