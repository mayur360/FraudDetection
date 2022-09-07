package com.example.demo.transaction;

import org.springframework.util.StringUtils;

public class TransactionUtil {

	public static double getTransactionLimitForTerminal(String terminalId) {
		double transactionLimit = 0;
		TerminalDetail termDetArr[] = TerminalDetail.values();
		for (int i = 0; i < termDetArr.length; i++) {
			TerminalDetail terminalDetail = termDetArr[i];
			if (terminalDetail.getTerminalId().equals(terminalId)) {
				transactionLimit = terminalDetail.getTransactionLimit();
				break;
			}
		}
		return transactionLimit;
	}

	public static boolean isValidTerminalID(String terminalId) {
		boolean isValidTerminal = false;
		TerminalDetail termDetArr[] = TerminalDetail.values();
		for (int i = 0; i < termDetArr.length; i++) {
			TerminalDetail terminalDetail = termDetArr[i];
			if (terminalDetail.getTerminalId().equals(terminalId)) {
				isValidTerminal = true;
				break;
			}
		}
		return isValidTerminal;
	}

	public static boolean isMandatoryDetailsPresent(TransactionDetails transactionDetails) {
		boolean isValidInput = true;
		if (transactionDetails == null) {
			return false;
		}

		if (!StringUtils.hasText(transactionDetails.getTerminalId())
				|| !StringUtils.hasText(transactionDetails.getCurrency()) || transactionDetails.getAmount() <= 0
				|| transactionDetails.getCardNumber() == null || transactionDetails.getCardNumber().longValue() <= 0) {
			return false;
		}
		return isValidInput;
	}

}
