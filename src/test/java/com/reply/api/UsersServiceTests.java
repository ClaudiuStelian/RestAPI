package com.reply.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.reply.api.models.ResponseMessage;
import com.reply.api.models.User;
import com.reply.api.repositories.UsersRepository;
import com.reply.api.services.UsersService;

@SpringBootTest
public class UsersServiceTests {

	@Test
	void registerUser_Should_Return_ResponseMessge_With_Created_StatusCode_When_Valid_User() {
		// arrange
		UsersService service = new UsersService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1978-02-20");

		// act
		ResponseMessage response = service.registerUser(user);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.CREATED);
		assertEquals(response.getErrorMessage(), null);
		assertEquals(response.getPayload(), user);
	}

	@Test
	void registerUser_Should_Return_ResponseMessge_With_Bad_Request_StatusCode_When_Invalid_User() {
		// arrange
		UsersService service = new UsersService();
		User user = new User();
		user.setUsername("validuser name");

		// act
		ResponseMessage response = service.registerUser(user);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getErrorMessage(), "Invalid user input");
		assertEquals(response.getPayload(), null);
	}

	@Test
	void registerUser_Should_Return_ResponseMessge_With_Forbidden_StatusCode_When_User_IS_Younger_Than_18() {
		// arrange
		UsersService service = new UsersService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("2022-02-20");

		// act
		ResponseMessage response = service.registerUser(user);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.FORBIDDEN);
		assertEquals(response.getErrorMessage(), "User under 18 is not allowed!");
		assertEquals(response.getPayload(), null);
	}

	@Test
	void registerUser_Should_Return_ResponseMessge_With_Conflict_StatusCode_When_UserName_IS_Already_Existing() {
		// arrange
		UsersService service = new UsersService();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");

		service.registerUser(user);

		User user1 = new User();
		user1.setUsername("validusername");
		user1.setPassword("passwordValid2");
		user1.setEmail("abc2@gmail");
		user1.setDob("1993-02-20");

		// act
		ResponseMessage response = service.registerUser(user1);

		// assert
		assertEquals(response.getHttpStatus(), HttpStatus.CONFLICT);
		assertEquals(response.getErrorMessage(), "Username is already existing!");
		assertEquals(response.getPayload(), null);
	}

	@Test
	void getUsers_Should_Return_All_Users_When_No_Credit_Card_Filter() {
		// arrange
		UsersService service = new UsersService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");

		service.registerUser(user);
		String creditCard = null;

		// act
		ArrayList<User> response = service.getUsers(creditCard);

		// assert
		assertEquals(response.size(), 1);
		User responseUser = response.get(0);
		assertEquals(responseUser.getUsername(), "validusername");
		assertEquals(responseUser.getPassword(), "passwordValid1");
		assertEquals(responseUser.getEmail(), "abc@gmail");
		assertEquals(responseUser.getDob(), "1992-02-20");
	}

	@Test
	void getUsers_Should_Return_All_Users_With_CardNo_When_CreditCard_Filter_Is_Yes() {
		// arrange
		UsersService service = new UsersService();
		UsersRepository.users =  new ArrayList<User>();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");
		user.setCardNo("1439864709184759");

		User user1 = new User();
		user1.setUsername("validusername1");
		user1.setPassword("passwordValid1");
		user1.setEmail("abc@gmail");
		user1.setDob("1992-02-20");

		service.registerUser(user);
		service.registerUser(user1);

		String creditCard = "Yes";

		// act
		ArrayList<User> response = service.getUsers(creditCard);
		
		// assert
		assertEquals(response.size(), 1);
		User responseUser = response.get(0);
		assertEquals(responseUser.getUsername(), "validusername");
		assertEquals(responseUser.getPassword(), "passwordValid1");
		assertEquals(responseUser.getEmail(), "abc@gmail");
		assertEquals(responseUser.getDob(), "1992-02-20");
		assertEquals(responseUser.getCardNo(), "1439864709184759");
	}

	@Test
	void getUsers_Should_Return_All_Users_Without_CardNo_When_CreditCard_Filter_Is_No() {
		// arrange
		UsersService service = new UsersService();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");
		user.setCardNo("1439864709184759");

		User user1 = new User();
		user1.setUsername("validusername1");
		user1.setPassword("passwordValid1");
		user1.setEmail("abc@gmail");
		user1.setDob("1992-02-20");

		service.registerUser(user);
		service.registerUser(user1);

		String creditCard = "No";

		// act
		ArrayList<User> response = service.getUsers(creditCard);

		// assert
		assertEquals(response.size(), 1);
		User responseUser = response.get(0);
		assertEquals(responseUser.getUsername(), "validusername1");
		assertEquals(responseUser.getPassword(), "passwordValid1");
		assertEquals(responseUser.getEmail(), "abc@gmail");
		assertEquals(responseUser.getDob(), "1992-02-20");
		assertEquals(responseUser.getCardNo(), null);
	}

	@Test
	void getUsers_Should_Return_Null_When_Invalid_Credit_Card_Filter() {
		// arrange
		UsersService service = new UsersService();
		User user = new User();
		user.setUsername("validusername");
		user.setPassword("passwordValid1");
		user.setEmail("abc@gmail");
		user.setDob("1992-02-20");
		user.setCardNo("1439864709184759");

		service.registerUser(user);

		String creditCard = "Invalid";

		// act
		ArrayList<User> response = service.getUsers(creditCard);

		// assert
		assertEquals(response, null);

	}
}
