package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.Recharge;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Long> {

}
