package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EducationalRegion implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String regionName;
	
	int cantCircuits;
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getRegionName() {
		return regionName;
	}




	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}




	public int getCantCircuits() {
		return cantCircuits;
	}




	public void setCantCircuits(int cantCircuits) {
		this.cantCircuits = cantCircuits;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
