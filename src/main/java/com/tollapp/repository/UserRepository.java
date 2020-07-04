package com.tollapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select user.vehicles from User user where user.id=?1")
	List<Vehicle> getUserVehicles(Long userId);

	@Query("Select user.id from User user where user.email=?1")
	Long getUserIdEmail(String email);

	@Query("Select user.id from User user where user.contact=?1")
	Long checkIfContactExist(String contact);

	@Query("Select user.id from User user where user.email=?1")
	Long checkIfEmailExist(String contact);

}
