package com.example.demo.transaction;

public class TransactionOutput {
	
	private long txnId;
	private String status;
	private String message;
	private int fraudScore;
	
	public long getTxnId() {
		return txnId;
	}
	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getFraudScore() {
		return fraudScore;
	}
	public void setFraudScore(int fraudScore) {
		this.fraudScore = fraudScore;
	}

}
