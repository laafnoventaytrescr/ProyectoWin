package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mediation_strategies")
public class MediationStrategies implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mediation_strategies_id")
	private Long idMediationStrategies;
	
    @ElementCollection
    @CollectionTable(name = "strategy_skill", joinColumns = @JoinColumn(name = "mediation_strategies_id"))
    @Column(name = "skill")
    private List<String> skills;

    @ElementCollection
    @CollectionTable(name = "strategy_working_days", joinColumns = @JoinColumn(name = "mediation_strategies_id"))
    @Column(name = "working_days")
    private List<String> workingDays;
    
    @ElementCollection
    @CollectionTable(name = "strategy_holydays", joinColumns = @JoinColumn(name = "mediation_strategies_id"))
    @Column(name = "holydays")
    private List<String> holydays;
    
	private String content;
	
	@Column(name="holy_day_option")
	private String holyDayOption;
	
	private String resources;
	
	private String interest;
	
	private String unit;
	
	private String strategy;
	
	private String indicator;
	
	private String comment;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="correlation_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Correlation correlation;
	

	public Long getIdMediationStrategies() {
		return idMediationStrategies;
	}

	public void setIdMediationStrategies(Long idMediationStrategies) {
		this.idMediationStrategies = idMediationStrategies;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Correlation getCorrelation() {
		return correlation;
	}

	public void setCorrelation(Correlation correlation) {
		this.correlation = correlation;
	}


	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getHolyDayOption() {
		return holyDayOption;
	}

	public void setHolyDayOption(String holyDayOption) {
		this.holyDayOption = holyDayOption;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(List<String> workingDays) {
		this.workingDays = workingDays;
	}

	public List<String> getHolydays() {
		return holydays;
	}

	public void setHolydays(List<String> holydays) {
		this.holydays = holydays;
	}
	
	
	
}
