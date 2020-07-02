package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.TollPlaza;

@Repository
public interface TollPlazaRepository extends JpaRepository<TollPlaza, Long> {

}
