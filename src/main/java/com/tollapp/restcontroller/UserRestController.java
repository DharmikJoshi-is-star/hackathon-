package com.tollapp.restcontroller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/registerUser")
	public Long registerUser(@RequestBody User user) {
		/*{
			"name":"Dharmik Joshi",
			"email":"dharmikj75@gmail.com",
			"contact": "9096167416",
			"username": "dharmik_joshi",
			"password": "dharmik@20"
			
		}
		*/	
		return userService.registerUser(user);
	}
	
	@PostMapping("/addBalance/{userId}")
	public void addBalance(@RequestBody Double balance, @PathVariable("userId") Long userId) {
		userService.addBalance(userId, balance);
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
		 * 
			{
				"email":"dharmikj75@gmail.com"
			}
		 * 
		 */
		
		return userService.isEmailRegistered(email);
		
		

	}
	
	@PostMapping("/sendOTP")
	public boolean sendOTP(@RequestBody String email){
		
		System.out.println(email);
		
		OTP otp = otpService.generateOTP(email);
		
		otp = otpService.saveOtp(otp);
		
		try {
			emailService.sendmail(email, otp.getOtp());
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
}
