package com.example.demo.transaction;

import java.math.BigInteger;

public class TransactionDetails {
	
	private long txnId;
	private double amount;
	private String currency;
	private String terminalId;
	private int threatScore;
	private BigInteger cardNumber;
	
	public long getTxnId() {
		return txnId;
	}
	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public int getThreatScore() {
		return threatScore;
	}
	public void setThreatScore(int threatScore) {
		this.threatScore = threatScore;
	}
	public BigInteger getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(BigInteger cardNumber) {
		this.cardNumber = cardNumber;
	}
}
