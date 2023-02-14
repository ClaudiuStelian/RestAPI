package com.reply.api.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;

public class UserInputValidator extends InputValidator {

	public static boolean existingUsername(String username) {

		for (User user : UsersRepository.users) {
			if (user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateInput(User user) {

		if (user == null) {
			return false;
		}

		String username = user.getUsername();
		if (username == null || username.isEmpty() || username.trim().isEmpty() || username.contains(" ")) {
			return false;
		}

		String pass = user.getPassword();
		if (pass == null || pass.length() < 8 || !containsUpperCaseCharacter(pass) || !containsDigit(pass)) {
			return false;
		}

		String email = user.getEmail();
		if (email == null || !isValidEmail(email)) {
			return false;
		}

		String dob = user.getDob();
		if (dob == null || !isValidDob(dob)) {
			return false;
		}

		String cardNo = user.getCardNo();
		if (cardNo != null) {

			if (cardNo.length() != 16 || !containsOnlyDigits(cardNo)) {
				return false;
			}
		}

		return true;
	}

	public static boolean hasValidAge(String dob) {
		int currentYear = Year.now().getValue();
		int userYear = LocalDate.parse(dob).getYear();

		if (currentYear - userYear < 18) {
			return false;
		}

		return true;
	}

	private static boolean isValidDob(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
