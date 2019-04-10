package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users")
public class User implements Serializable{                                //Entidad Usuario y persistencia con la base

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long userId;
	
	@Column(unique=true)
	String email;
	
	@Column(unique=true)
	String idNumber;
	
	String name;
	
	@Temporal(TemporalType.DATE)
	Date birthDate;
	
	String regionalDirection;
	
	String circuito;
	
	String school;
	
	String levelOfTeaches;
	
	String password;
	
	String tempPassword;
	
	String role;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRegionalDirection() {
		return regionalDirection;
	}

	public void setRegionalDirection(String regionalDirection) {
		this.regionalDirection = regionalDirection;
	}

	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLevelOfTeaches() {
		return levelOfTeaches;
	}

	public void setLevelOfTeaches(String levelOfTeaches) {
		this.levelOfTeaches = levelOfTeaches;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
