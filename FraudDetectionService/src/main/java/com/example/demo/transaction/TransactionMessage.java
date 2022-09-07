package com.example.demo.transaction;

public enum TransactionMessage {

	MESSAGE_1 ("Mandatory input transaction details missing"),
	MESSAGE_2 ("Unknown terminal"),
	MESSAGE_3 ("Amount too large for this terminal"),
	MESSAGE_4 ("Transaction approved"),
	MESSAGE_5 ("System error, contact administrator");
	
	private String message;
	
	TransactionMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
