package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KnowAboutTheme")
public class KnowAboutTheme implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long idKnowAboutTheme;
	
	String description;
	
	public Long getIdKnowAboutTheme() {
		return idKnowAboutTheme;
	}

	public void setIdKnowAboutTheme(Long idKnowAboutTheme) {
		this.idKnowAboutTheme = idKnowAboutTheme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
