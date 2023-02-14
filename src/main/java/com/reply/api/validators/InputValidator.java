package com.reply.api.validators;

import java.util.regex.Pattern;

public class InputValidator {

	public static boolean containsUpperCaseCharacter(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}

		return false;
	}
	
	public static boolean containsOnlyDigits(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}

		return true;
	}
	
	public static boolean isValidEmail(String emailAddress) {
		String regexPattern = "^(.+)@(\\S+)$";
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}
	
	public static boolean containsDigit(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				return true;
			}
		}

		return false;
	}
}
