package com.reply.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;
import com.reply.api.validators.UserInputValidator;

@SpringBootTest
public class UserInputValidatorTests {

	@Test
	void validateInput_Should_Return_False_When_User_Is_Null() {
		// arrange
		User user = null;
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Usename_Contains_Spaces() {
		// arrange
		User user = new User();
		user.setUsername("in valid");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	
	@Test
	void validateInput_Should_Return_False_When_Password_Has_Less_Than_8_Characters() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("pass");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Password_Has_No_Uppercase() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordvalid");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Password_Has_No_Digit() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Invalid_Email() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_Dob_Format_Not_ISO8601() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("20-FEB-1978");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_CardNo_Exits_And_Lenght_Less_Than_16_Characters() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1978-02-20");
		user.setCardNo("12345");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void validateInput_Should_Return_False_When_CardNo_Exits_And_Has_Not_Only_Digits() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1978-02-20");
		user.setCardNo("123456789111111A");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	
	@Test
	void validateInput_Should_Return_True_When_User_Input_Is_Correct_And_Has_CardNo() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1978-02-20");
		user.setCardNo("1234567891111112");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, true);
	}
	
	
	@Test
	void validateInput_Should_Return_True_When_User_Input_Is_Correct_And_HasNot_CardNo() {
		// arrange
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1978-02-20");
		
		// act
		boolean isValid = UserInputValidator.validateInput(user);
		
		
		// assert
		assertEquals(isValid, true);
	}
	
	
	@Test
	void existingUsername_Should_Return_False_When_Username_Not_Existing() {
		// arrange
		String username = "test";
		UsersRepository.users = new ArrayList<User>();
		
		
		// act
		boolean isValid = UserInputValidator.existingUsername(username);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	@Test
	void existingUsername_Should_Return_True_When_Username_Existing() {
		// arrange
		String username = "test";		
		UsersRepository.users = new ArrayList<User>();
		User existingUser =  new User();
		existingUser.setUsername("test");
		UsersRepository.users.add(existingUser);		
		
		// act
		boolean isValid = UserInputValidator.existingUsername(username);
		
		
		// assert
		assertEquals(isValid, true);
	}
	
	
	@Test
	void hasValidAge_Should_Return_True_When_More_Than_18_years() {
		// arrange
		String dob = "1956-02-20";	
		
		// act
		boolean isValid = UserInputValidator.hasValidAge(dob);
		
		
		// assert
		assertEquals(isValid, true);
	}
	
	@Test
	void hasValidAge_Should_Return_False_When_Less_Than_18_years() {
		// arrange
		String dob = "2022-02-20";	
		
		// act
		boolean isValid = UserInputValidator.hasValidAge(dob);
		
		
		// assert
		assertEquals(isValid, false);
	}
	
	
	
}
