package com.example.demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.transaction.TransactionDetails;
import com.example.demo.transaction.TransactionOutput;
import com.example.demo.transaction.TransactionValidatorService;

@SpringBootTest
class FraudDetectionServiceApplicationTests {
	
	private static final Logger log = LogManager.getLogger(FraudDetectionServiceApplicationTests.class);
	public static final String SERVER_URI = "http://localhost:8080";
	
	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransactionValidatorService transactionValidatorService = new TransactionValidatorService();

	@Test
	void testValidTransactionService() {
		List<TransactionDetails> transactionDetailList = mockTransactions();
		for (TransactionDetails transactionDetails : transactionDetailList) {
			ResponseEntity<TransactionOutput> myEntity = new ResponseEntity<TransactionOutput>(HttpStatus.OK);
			Mockito.when(restTemplate.getForEntity(SERVER_URI + "/transaction/validate", TransactionOutput.class))
					.thenReturn(myEntity);
			TransactionOutput response = transactionValidatorService.validateTransactionDetails(transactionDetails);
			log.info("TXNID:" + response.getTxnId());
			log.info("SCORE:" + response.getFraudScore());
			log.info("MESSAGE:" + response.getMessage());
			log.info("STATUS:" + response.getStatus());
			log.info("TXN Output ENDS" + "\n");
		}
	}
	
	private static List<TransactionDetails> mockTransactions() {
		List<TransactionDetails> transactionDetailList = new ArrayList<>();
		
		//Good TXN
		TransactionDetails txn1 = new TransactionDetails();
		txn1.setAmount(50000);
		txn1.setCurrency("USD");
		txn1.setCardNumber(new BigInteger("1088198815289091"));
		txn1.setTerminalId("U478910");
		txn1.setTxnId(178991);
		transactionDetailList.add(txn1);
		
		//Error TXN, invalid Terminal
		TransactionDetails txn2 = new TransactionDetails();
		txn2.setAmount(50000);
		txn2.setCurrency("USD");
		txn2.setCardNumber(new BigInteger("1088198815289091"));
		txn2.setTerminalId("U4789100000");
		txn2.setTxnId(178992);
		transactionDetailList.add(txn2);
		
		//Good TXN, other terminal
		TransactionDetails txn3 = new TransactionDetails();
		txn3.setAmount(1500000);
		txn3.setCurrency("USD");
		txn3.setCardNumber(new BigInteger("1088198815289091"));
		txn3.setTerminalId("SU478910");
		txn3.setTxnId(178993);
		transactionDetailList.add(txn3);
		
		//Error TXN, invalid Terminal amount
		TransactionDetails txn4 = new TransactionDetails();
		txn4.setAmount(2500000);
		txn4.setCurrency("USD");
		txn4.setCardNumber(new BigInteger("1088198815289091"));
		txn4.setTerminalId("SU878910");
		txn4.setTxnId(178994);
		transactionDetailList.add(txn4);
		
		//Error TXN, missing input details
		TransactionDetails txn5 = new TransactionDetails();
		txn5.setAmount(-1);
		txn5.setCurrency(null);
		txn5.setCardNumber(null);
		txn5.setTerminalId(null);
		txn5.setTxnId(0);
		transactionDetailList.add(txn5);
		
		return transactionDetailList;
	}

}
