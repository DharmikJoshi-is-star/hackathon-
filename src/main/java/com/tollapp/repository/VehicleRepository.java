package com.tollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tollapp.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	@Query("Select vehicle from Vehicle vehicle where vehicle.registrationNo=?1")
	Vehicle findVehicle(String registrationNo);

}
