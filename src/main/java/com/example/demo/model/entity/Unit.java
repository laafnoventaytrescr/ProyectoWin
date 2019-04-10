package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Units")
public class Unit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idUnits")
	private Long idUnit;

	public Long getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(Long idUnit) {
		this.idUnit = idUnit;
	}
	
	
}
