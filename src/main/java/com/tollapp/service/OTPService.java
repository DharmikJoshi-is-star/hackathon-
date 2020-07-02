package com.tollapp.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.OTP;
import com.tollapp.repository.OTPRepository;

@Service
public class OTPService {

	@Autowired
	OTPRepository otpRepository;
	
	public OTP generateOTP(String email) {	
		Random random = new Random();

		String otpText = "";
		while(otpText.length()!=6) {
			otpText = otpText + random.nextInt(9);
			System.out.println(otpText);
		}

		Long dateTime = System.currentTimeMillis();
		
		OTP otp = new OTP();
		otp.setEmail(email);
		otp.setOtp(otpText);
		otp.setDate(new Date(dateTime));
		otp.setTime(new Time(dateTime));
	
		return otp;   
	}

	public OTP saveOtp(OTP otp) {
		
		return otpRepository.save(otp);
	}

	public boolean verifyOTP(String otpStr) {
		
		OTP otp = otpRepository.findOTP(otpStr);
		
		if(otp!=null) {
			otpRepository.delete(otp);
			return true;
		}
		
		return false;
	}
	
}
