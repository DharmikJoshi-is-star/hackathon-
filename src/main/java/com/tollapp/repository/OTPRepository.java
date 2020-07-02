package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long>{

	@Query("Select otpObj from OTP otpObj where otpObj.otp=?1")
	OTP findOTP(String otpStr);

}
