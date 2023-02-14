package com.reply.api.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reply.api.models.ResponseMessage;
import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;
import com.reply.api.validators.UserInputValidator;

@Service
public class UsersService {


	public ResponseMessage registerUser(User user) {

		boolean isValid = UserInputValidator.validateInput(user);

		if (isValid) {
			if (!UserInputValidator.hasValidAge(user.getDob())) {
				return new ResponseMessage(HttpStatus.FORBIDDEN, "User under 18 is not allowed!", null);
			}

			if (UserInputValidator.existingUsername(user.getUsername())) {
				return new ResponseMessage(HttpStatus.CONFLICT, "Username is already existing!", null);
			}

			UsersRepository.users.add(user);
			return new ResponseMessage(HttpStatus.CREATED, null, user);

		} else {
			return new ResponseMessage(HttpStatus.BAD_REQUEST, "Invalid user input", null);
		}
	}

	public ArrayList<User> getUsers(String creditCard) {

		if (creditCard == null) {
			return UsersRepository.users;
		} else {

			switch (creditCard.toLowerCase()) {
			case "yes":
				return UsersRepository.users.stream().filter(u -> u.getCardNo() != null)
						.collect(Collectors.toCollection(ArrayList::new));
			case "no":
				return UsersRepository.users.stream().filter(u -> u.getCardNo() == null)
						.collect(Collectors.toCollection(ArrayList::new));
			}
			return null;
		}

	}

}
