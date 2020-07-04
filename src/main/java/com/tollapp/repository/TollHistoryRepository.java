package com.tollapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.TollHistory;

@Repository
public interface TollHistoryRepository extends JpaRepository<TollHistory, Long>{

	@Query("Select tollHistory from TollHistory tollHistory where tollHistory.tollPlaza.id=?1")
	List<TollHistory> fetchHistoryFromToll(Long tollPlazaId);

}
