package com.reply.api.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.reply.api.models.ResponseMessage;
import com.reply.api.models.User;
import com.reply.api.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService _userService;
	
	
	@RequestMapping(value = {"", "/{creditcard}"})
	public ArrayList<User> getUsers(@PathVariable(required = false) String creditcard) {
		return _userService.getUsers(creditcard);
	}
	
	@PostMapping()
	public ResponseMessage registerUser( @RequestBody User user) {
		
		return _userService.registerUser(user);		
	}
	
}
