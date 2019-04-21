package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.EducationalRegion;
import com.example.demo.model.service.IEducationalRegionService;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/v1")
public class EducationalRegionController {
	
	@Autowired(required=true)
	private IEducationalRegionService educationalRegionService;                           
	
	@GetMapping("/EducationalRegion")
	public List<EducationalRegion> index(){
		return educationalRegionService.findAll();                                     
	}

	
}
