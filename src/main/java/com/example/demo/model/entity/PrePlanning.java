package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PrePlanning")
public class PrePlanning implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPrePlanning;

	String mainLikeToKnow;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="planning_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Planning idPlan;
	
	@NotNull(message="No puede ser nula")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="know_about_theme_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	KnowAboutTheme idKnowAboutTheme;
	
	@NotNull(message="No puede ser nula")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="like_to_know_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	LikeToKnow idLikeToKnow;

	@NotNull(message="No puede ser nula")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categorization_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	Categorization idCategorization;

	public Long getIdPrePlanning() {
		return idPrePlanning;
	}

	public void setIdPrePlanning(Long idPrePlanning) {
		this.idPrePlanning = idPrePlanning;
	}

	public String getMainLikeToKnow() {
		return mainLikeToKnow;
	}

	public void setMainLikeToKnow(String mainLikeToKnow) {
		this.mainLikeToKnow = mainLikeToKnow;
	}

	public Planning getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Planning idPlan) {
		this.idPlan = idPlan;
	}

	public KnowAboutTheme getIdKnowAboutTheme() {
		return idKnowAboutTheme;
	}

	public void setIdKnowAboutTheme(KnowAboutTheme idKnowAboutTheme) {
		this.idKnowAboutTheme = idKnowAboutTheme;
	}

	public LikeToKnow getIdLikeToKnow() {
		return idLikeToKnow;
	}

	public void setIdLikeToKnow(LikeToKnow idLikeToKnow) {
		this.idLikeToKnow = idLikeToKnow;
	}

	public Categorization getIdCategorization() {
		return idCategorization;
	}

	public void setIdCategorization(Categorization idCategorization) {
		this.idCategorization = idCategorization;
	}






	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
