package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="correlation")
public class Correlation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="correlation_id")
	private Long correlationId;
	
	private String concept;
	
	private String level;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pre_planning_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PrePlanning prePlanning;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="unit_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Unit unit;

	public Long getIdCorrelation() {
		return correlationId;
	}

	public void setIdCorrelation(Long idCorrelation) {
		this.correlationId = idCorrelation;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	public PrePlanning getPrePlanning() {
		return prePlanning;
	}

	public void setPrePlanning(PrePlanning prePlanning) {
		this.prePlanning = prePlanning;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
}
