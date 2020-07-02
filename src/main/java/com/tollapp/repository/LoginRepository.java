package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("Select login.userId from Login login where login.username=?1 and login.password=?2")
	Long checkCredentials(String username, String password);

}
