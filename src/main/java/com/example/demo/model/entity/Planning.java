package com.example.demo.model.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="plan")
public class Planning implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPlan;
	
	private String theme;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	private String durationWeeks;
	
	private String optionWork;
	
	private String educationalPeriod;
	
	private String regionalDirection;
	
	private String circuit;
	
	private String school;
	
	private String levelOfTeaches;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private User user;
		
	public Long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDurationWeeks() {
		return durationWeeks;
	}

	public void setDurationWeeks(String durationWeeks) {
		this.durationWeeks = durationWeeks;
	}

	public String getOptionWork() {
		return optionWork;
	}

	public void setOptionWork(String optionWork) {
		this.optionWork = optionWork;
	}

	public String getEducationalPeriod() {
		return educationalPeriod;
	}

	public void setEducationalPeriod(String educationalPeriod) {
		this.educationalPeriod = educationalPeriod;
	}
	

	public String getRegionalDirection() {
		return regionalDirection;
	}

	public void setRegionalDirection(String regionalDirection) {
		this.regionalDirection = regionalDirection;
	}

	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
