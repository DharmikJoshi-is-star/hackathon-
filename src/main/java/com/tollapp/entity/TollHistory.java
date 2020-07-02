package com.tollapp.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TollHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tollPlaza_id")
	private TollPlaza tollPlaza;

	private Double tollPrize;
	
	private Date date;
	
	private Time time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public TollPlaza getTollPlaza() {
		return tollPlaza;
	}

	public void setTollPlaza(TollPlaza tollPlaza) {
		this.tollPlaza = tollPlaza;
	}

	public Double getTollPrize() {
		return tollPrize;
	}

	public void setTollPrize(Double tollPrize) {
		this.tollPrize = tollPrize;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	
	
}
