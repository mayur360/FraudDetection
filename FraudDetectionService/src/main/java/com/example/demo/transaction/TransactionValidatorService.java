package com.example.demo.transaction;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionValidatorService {
	
	private static String TXN_STATUS_ACCEPT = "ACCEPTED";
	private static String TXN_STATUS_DECLINED= "DECLINED";
	
	@RequestMapping(value = "/transaction/validate", method=RequestMethod.POST)
	public TransactionOutput validateTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
		TransactionOutput transactionOutput = new TransactionOutput();
		try {
			//validating for mandatory input values
			if (TransactionUtil.isMandatoryDetailsPresent(transactionDetails)) {
				
				//validating for valid terminal
				if (!TransactionUtil.isValidTerminalID(transactionDetails.getTerminalId())) {
					transactionOutput = generateDeclinedTxnOutput(90, TransactionMessage.MESSAGE_2.getMessage(),
							transactionDetails.getTxnId());
				} else {
					//validating for transaction limit for terminal
					double limit = TransactionUtil.getTransactionLimitForTerminal(transactionDetails.getTerminalId());
					if (transactionDetails.getAmount() > limit) {
						//generating invalid txn output
						transactionOutput = generateDeclinedTxnOutput(100, TransactionMessage.MESSAGE_3.getMessage(),
								transactionDetails.getTxnId());
					} else {
						//generating valid txn output
						transactionOutput = generateAcceptedTxnOutput(0, TransactionMessage.MESSAGE_4.getMessage(),
								transactionDetails.getTxnId());
					}
				}
			} else {
				//generating invalid txn output
				transactionOutput = generateDeclinedTxnOutput(50, TransactionMessage.MESSAGE_1.getMessage(),
						transactionDetails.getTxnId());
			}
		} catch (Exception e) {
			System.err.print(e.getCause());
			transactionOutput = generateDeclinedTxnOutput(50, TransactionMessage.MESSAGE_5.getMessage(),
					transactionDetails.getTxnId());
		}
		return transactionOutput;
	}
	
	private TransactionOutput generateDeclinedTxnOutput(int score, String message, long txnId) {
		TransactionOutput transactionOutput = new TransactionOutput();  
		transactionOutput.setMessage(message);
		transactionOutput.setStatus(TXN_STATUS_DECLINED);
		transactionOutput.setFraudScore(score);
		transactionOutput.setTxnId(txnId);
		return transactionOutput;
	}
	
	private TransactionOutput generateAcceptedTxnOutput(int score, String message, long txnId) {
		TransactionOutput transactionOutput = new TransactionOutput();  
		transactionOutput.setMessage(message);
		transactionOutput.setStatus(TXN_STATUS_ACCEPT);
		transactionOutput.setFraudScore(score);
		transactionOutput.setTxnId(txnId); 
		return transactionOutput;
	}
}
