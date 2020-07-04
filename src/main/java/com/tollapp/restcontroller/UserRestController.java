package com.tollapp.restcontroller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.entity.OTP;
import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.service.EmailService;
import com.tollapp.service.OTPService;
import com.tollapp.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	OTPService otpService;
	
	
	///copy this
	@PostMapping("/login")
	public Long checkCredentials(@RequestBody String[] usernamePassword) {
		Long userId = userService.checkCredentials(usernamePassword[0], usernamePassword[1]);
		return userId;
	}
	
	@PostMapping("/registerUser")
	public Long registerUser(@RequestBody User user) {
		/*{
			"name":"Dharmik Joshi",
			"email":"dharmikj75@gmail.com",
			"contact": "1234567890",
			"username": "dharmik_joshi",
			"password": "dharmik@20"
		}
		*/	
		return userService.registerUser(user);
	}
	
	
	//new API
	@PostMapping("/verifyContact")
	public boolean isContactExists(@RequestBody String contact) {
		
		return userService.isContactExists(contact);
		
	}
	
	@PostMapping("/addBalance/{userId}")
	public Double addBalance(@RequestBody Double balance, @PathVariable("userId") Long userId) {
		// 20.00
		return userService.addBalance(userId, balance);
	}
	
	@GetMapping("/getUser/{userId}")
	public User getUserWithId(@PathVariable("userId") Long userId) {
		return userService.getUserWithId(userId);
	}
	
	@GetMapping("/getUserVehicles/{userId}")
	public List<Vehicle> getUserVehicles(@PathVariable("userId") Long userId){
		return userService.getUserVehicles(userId);
	}
	
	@PostMapping("/isEmailRegistered")
	public boolean isEmailRegistered(@RequestBody String email) {
		/*
			"dharmikj75@gmail.com"
		 */		
		return userService.isEmailRegistered(email);

	}
	
	@PutMapping("/editContact/{userId}")
	public boolean editContact(@RequestBody String contact, @PathVariable("userId") Long userId) {
		return userService.editContact(userId, contact);
	}
	
	@PostMapping("/sendOTP")
	public boolean sendOTP(@RequestBody String email){
		
		System.out.println(email);
		
		OTP otp = otpService.generateOTP(email);
		
		try {
			emailService.sendmail(email, otp.getOtp());
			otp = otpService.saveOtp(otp);
		} catch (MailException | MessagingException e) {
			e.printStackTrace();	
			return false;
		}
		return true;
	}
	
	@PostMapping("/verifyingOTP")
	public boolean verifyingOTP(@RequestBody String otp) {
		
		if(otpService.verifyOTP(otp))
			return true;
		else 
			return false;

	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		
		userService.deleteUser(userId);
		
	}
	
}
