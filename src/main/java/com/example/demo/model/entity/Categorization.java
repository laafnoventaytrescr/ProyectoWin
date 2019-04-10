package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorization")
public class Categorization implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCategorization;

	String type;
	
	public Long getIdCategorization() {
		return idCategorization;
	}

	public void setIdCategorization(Long idCategorization) {
		this.idCategorization = idCategorization;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
