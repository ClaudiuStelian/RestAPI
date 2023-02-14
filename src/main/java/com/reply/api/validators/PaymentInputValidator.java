package com.reply.api.validators;

import com.reply.api.models.Payment;

public class PaymentInputValidator extends InputValidator {

	public static boolean validateInput(Payment payment) {
		if (payment == null) {
			return false;
		}
		
		String cardNo = payment.getCardNo();
		if(cardNo == null || cardNo.length() != 16 || !containsOnlyDigits(cardNo)) {
			return false;
		}
		
		String amount = payment.getAmount();
		if(amount == null || amount.length() != 3 || !containsOnlyDigits(amount)) {
			return false;
		}
		
		return true;
	}
}
