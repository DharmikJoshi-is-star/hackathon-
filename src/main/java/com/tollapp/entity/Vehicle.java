package com.tollapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Registration_Number")
	private String registrationNo;
	
	@Column(name = "Registration_Date")
	private String registrationDate;
	
	@Column(name = "Chasi_Number")
	private String chasiNo;
	
	@Column(name = "Engine_Number")
	private String engineNo;
	
	@Column(name = "Owner_Name")
	private String ownerName;
	
	@Column(name = "Vehicle_Class")
	private String vehicleClass;
	
	@Column(name = "Fuel_Type")
	private String fuelType;
	
	@Column(name = "Maker_Model")
	private String makerModel;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private List<TollHistory> tollHistories;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getChasiNo() {
		return chasiNo;
	}

	public void setChasiNo(String chasiNo) {
		this.chasiNo = chasiNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getMakerModel() {
		return makerModel;
	}

	public void setMakerModel(String makerModel) {
		this.makerModel = makerModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TollHistory> getTollHistories() {
		return tollHistories;
	}

	public void setTollHistories(List<TollHistory> tollHistories) {
		this.tollHistories = tollHistories;
	}


	
	
	
}
