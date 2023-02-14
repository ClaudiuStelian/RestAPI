package com.reply.api.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reply.api.models.Payment;
import com.reply.api.models.ResponseMessage;
import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;
import com.reply.api.validators.PaymentInputValidator;

@Service
public class PaymentsService {

	public ResponseMessage processPayment(Payment payment) {

		boolean isValid = PaymentInputValidator.validateInput(payment);

		if (!isValid) {
			return new ResponseMessage(HttpStatus.BAD_REQUEST, "Invalid payment input", null);
		}

		ArrayList<User> existingCardNos = UsersRepository.users.stream()
				.filter(u -> u.getCardNo() != null && u.getCardNo().equals(payment.getCardNo()))
				.collect(Collectors.toCollection(ArrayList::new));

		System.out.println(existingCardNos.size());

		if (existingCardNos.size() == 0) {
			return new ResponseMessage(HttpStatus.NOT_FOUND, "Card number not found!", null);
		}

		return new ResponseMessage(HttpStatus.CREATED, null, payment);
	}

}
