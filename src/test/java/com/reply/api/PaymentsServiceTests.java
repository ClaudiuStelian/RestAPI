package com.reply.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.reply.api.models.Payment;
import com.reply.api.models.ResponseMessage;
import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;
import com.reply.api.services.PaymentsService;
import com.reply.api.services.UsersService;

@SpringBootTest
public class PaymentsServiceTests {

	@Test
	void processPayment_Should_Return_ResponseMessge_With_Bad_Request_StatusCode_When_Invalid_User() {
		// arrange
		PaymentsService service = new PaymentsService();
		Payment payment = new Payment();
		payment.setAmount("12");

		// act
		ResponseMessage response = service.processPayment(payment);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getErrorMessage(), "Invalid payment input");
		assertEquals(response.getPayload(), null);
	}
	
	@Test
	void processPayment_Should_Return_ResponseMessge_With_Not_Found_StatusCode_When_CardNo_Not_Existing() {
		// arrange
		PaymentsService service = new PaymentsService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");
		user.setCardNo("1439864709184759");
		
		Payment payment = new Payment();
		payment.setAmount("123");
		payment.setCardNo("1234567891111112");

		UsersRepository.users.add(user);

		// act
		ResponseMessage response = service.processPayment(payment);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.NOT_FOUND);
		assertEquals(response.getErrorMessage(), "Card number not found!");
		assertEquals(response.getPayload(), null);
	}
	
	@Test
	void processPayment_Should_Return_ResponseMessge_With_Created_StatusCode_When_CardNo_Existing() {
		// arrange
		PaymentsService service = new PaymentsService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");
		user.setCardNo("1439864709184759");
		
		Payment payment = new Payment();
		payment.setAmount("123");
		payment.setCardNo("1439864709184759");

		UsersRepository.users.add(user);

		// act
		ResponseMessage response = service.processPayment(payment);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.CREATED);
		assertEquals(response.getErrorMessage(), null);
		assertEquals(response.getPayload(), payment);
	}
}
