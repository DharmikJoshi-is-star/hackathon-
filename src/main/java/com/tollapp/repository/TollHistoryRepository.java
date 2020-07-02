package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.TollHistory;

@Repository
public interface TollHistoryRepository extends JpaRepository<TollHistory, Long>{

}
