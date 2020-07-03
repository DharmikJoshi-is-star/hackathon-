package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.TollPlaza;

@Repository
public interface TollPlazaRepository extends JpaRepository<TollPlaza, Long> {

	@Query("Select tollPlaza from TollPlaza tollPlaza where tollPlaza.username=?1 and tollPlaza.password=?2")
	TollPlaza checkCredentials(String username, String password);

}
