<<<<<<< HEAD
package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="LikeToKnow")
public class LikeToKnow implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long idLikeToKnow;
	
	String description;
	
	/*@NotNull(message="No puede ser nula")*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categorization_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	Categorization categorization;
	
	public Long getIdLikeToKnow() {
		return idLikeToKnow;
	}



	public void setIdLikeToKnow(Long idLikeToKnow) {
		this.idLikeToKnow = idLikeToKnow;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Categorization getCategorization() {
		return categorization;
	}



	public void setCategorization(Categorization categorization) {
		this.categorization = categorization;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
=======
package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="LikeToKnow")
public class LikeToKnow implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long idLikeToKnow;
	
	String description;
	
	/*@NotNull(message="No puede ser nula")*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categorization_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	Categorization idCategorization;
	
	public Long getIdLikeToKnow() {
		return idLikeToKnow;
	}



	public void setIdLikeToKnow(Long idLikeToKnow) {
		this.idLikeToKnow = idLikeToKnow;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
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
>>>>>>> 8a1223219b23eed9712918b0000d7ac743f7bc47
