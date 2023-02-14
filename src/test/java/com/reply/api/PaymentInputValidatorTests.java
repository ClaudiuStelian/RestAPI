package com.reply.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reply.api.models.Payment;
import com.reply.api.models.User;
import com.reply.api.validators.PaymentInputValidator;
import com.reply.api.validators.UserInputValidator;

@SpringBootTest
public class PaymentInputValidatorTests {

	@Test
	void validateInput_Should_Return_False_When_Payment_Is_Null() {
		// arrange
		Payment payment = null;
		
		// act
		boolean isValid = PaymentInputValidator.validateInput(payment);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_CardNo_Lenght_Less_Than_16_Characters() {
		// arrange
		Payment payment = new Payment();
		payment.setAmount("123");
		payment.setCardNo("12345");
		
		// act
		boolean isValid = PaymentInputValidator.validateInput(payment);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_CardNo_Has_Not_Only_Digits() {
		// arrange
		Payment payment = new Payment();
		payment.setAmount("123");
		payment.setCardNo("123456789111111A");
		
		// act
		boolean isValid = PaymentInputValidator.validateInput(payment);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Amount_Not_3_Characters() {
		// arrange
		Payment payment = new Payment();
		payment.setAmount("12");
		payment.setCardNo("1234567891111112");
		
		// act
		boolean isValid = PaymentInputValidator.validateInput(payment);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Amount_Has_Not_Only_Digits() {
		// arrange
		Payment payment = new Payment();
		payment.setAmount("1A3");
		payment.setCardNo("1234567891111112");
		
		// act
		boolean isValid = PaymentInputValidator.validateInput(payment);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
}
