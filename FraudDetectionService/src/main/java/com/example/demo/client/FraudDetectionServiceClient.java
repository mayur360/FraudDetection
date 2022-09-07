package com.example.demo.client;

import java.math.BigInteger;

import org.springframework.web.client.RestTemplate;

import com.example.demo.transaction.TransactionDetails;
import com.example.demo.transaction.TransactionOutput;

public class FraudDetectionServiceClient {

	public static final String SERVER_URI = "http://localhost:8080";
	

	//un-comment below line to test the service
//	public static void main(String[] args) {
//		TransactionDetails transactionDetails = getTransaction();
//		TransactionOutput response = validateTransaction(transactionDetails);
//		System.out.println("TXNID:" + response.getTxnId());
//		System.out.println("SCORE:" + response.getFraudScore());
//		System.out.println("MESSAGE:" + response.getMessage());
//		System.out.println("STATUS:" + response.getStatus());
//
//	}


	private static TransactionOutput validateTransaction(TransactionDetails transactionDetails) {
		RestTemplate restTemplate = new RestTemplate();
		TransactionOutput response = restTemplate.postForObject(SERVER_URI + "/transaction/validate",
				transactionDetails, TransactionOutput.class);
		return response;
	}

	private static TransactionDetails getTransaction() {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setAmount(50000);
		transactionDetails.setCurrency("USD");
		transactionDetails.setCardNumber(new BigInteger("1088198815289091"));
		transactionDetails.setTerminalId("U478910");
		transactionDetails.setTxnId(178991);
		return transactionDetails;
	}

}
