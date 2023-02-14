package com.reply.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reply.api.models.Payment;
import com.reply.api.models.ResponseMessage;
import com.reply.api.services.PaymentsService;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

	@Autowired
	private PaymentsService _paymentsService;
	
	@PostMapping()
	public ResponseMessage processPayment(@RequestBody Payment payment) {
		return _paymentsService.processPayment(payment);
	}
}
