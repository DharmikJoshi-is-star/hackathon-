package com.tollapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TollPlaza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	@Column(name="Toll_Price")
	private Double tollPrice;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tollPlaza")
	private List<TollHistory> tollHistories;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTollPrice() {
		return tollPrice;
	}

	public void setTollPrice(Double tollPrice) {
		this.tollPrice = tollPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TollHistory> getTollHistories() {
		return tollHistories;
	}

	public void setTollHistories(List<TollHistory> tollHistories) {
		this.tollHistories = tollHistories;
	}
	
}
