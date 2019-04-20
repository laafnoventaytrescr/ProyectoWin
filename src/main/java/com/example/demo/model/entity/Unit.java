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
@Table(name="units")
public class Unit implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="unit_id")
	private Long idUnit;
	
	private String name;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_at")
	private Date modifiedAt;
	
	@Column(name="identity_version")
	private Long identityVersion;
	
	@Column(name="unit_order")
	private Long unitOrder;

	public Long getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(Long idUnit) {
		this.idUnit = idUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Long getIdentityVersion() {
		return identityVersion;
	}

	public void setIdentityVersion(Long identityVersion) {
		this.identityVersion = identityVersion;
	}

	public Long getUnitOrder() {
		return unitOrder;
	}

	public void setUnitOrder(Long unitOrder) {
		this.unitOrder = unitOrder;
	}
	
	
}
