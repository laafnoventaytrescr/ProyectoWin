package com.example.demo.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StaticSpring;
import com.example.demo.auth.service.JWTService;
import com.example.demo.model.entity.Correlation;
import com.example.demo.model.entity.LikeToKnow;
import com.example.demo.model.entity.MediationStrategies;
import com.example.demo.model.entity.Planning;
import com.example.demo.model.entity.Unit;
import com.example.demo.model.error.DataAccessRuntimeException;
import com.example.demo.model.service.ICorrelationService;
import com.example.demo.model.service.ILikeToKnowService;
import com.example.demo.model.service.IMediationStrategiesService;
import com.example.demo.model.service.IPlanningService;
import com.example.demo.model.service.IUnitService;
import com.example.demo.model.service.IUserService;

@RestController
@RequestMapping(value="/v1/mediationStrategies")
public class MediationStrategiesController {

	@Autowired
	IMediationStrategiesService mediationStrategies;
	
	@Autowired
	JWTService tokenService;
	
	@Autowired
	IUserService userService; 
	
	@Autowired
	IUnitService unitDao;
	
	@Autowired
	ICorrelationService correlationDao;
	
	@Autowired
	ILikeToKnowService likeToKnowDao;
	
	@Autowired
	IPlanningService planningDao;
	
	@GetMapping(value="/strategies")
	public ResponseEntity<List<MediationStrategies>> loadStrategies(HttpServletRequest request){
		
		Long userId = getUserIdByToken(request);
		
		List<MediationStrategies> strategy = null;
		
		try {     
			
		    strategy = mediationStrategies.findMediationStrategiesByUser(userId);                    //Metodo para cargar todas las estrategias de un usuario
		
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");
		}
		
		if(!strategy.isEmpty()) {
			return new ResponseEntity<>(strategy,HttpStatus.OK);  
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping(value="/getStrategy/{id}")
	public ResponseEntity<MediationStrategies> getPlan(@PathVariable Long id){
		
		MediationStrategies strategy = null;
		
		try {
			
    		strategy = mediationStrategies.findById(id);
    		
    		}catch(DataAccessException ex) {
    			throw new DataAccessRuntimeException(ex.getMessage());                  //Metodo para obtener una estrategia mediante id
    		}
    		
    		if(strategy != null) {
    			return new ResponseEntity<>(strategy,HttpStatus.OK);  
    		}else {
    			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);  
    		}
	}
	
	@GetMapping(value="/createStrategy/{planId}")
	public ResponseEntity<List<Object>> loadStrategy(@PathVariable Long planId, HttpServletRequest request){
			
		Long userId = getUserIdByToken(request);
		
	    
		Map<String, String> response = new HashMap<String, String>();
		List<Correlation> correlation;
		List<LikeToKnow> likeToKnow;
		List<Unit> units;
		Planning plan;
		List<String> fechas = new ArrayList<>();
		List<Object> responseJson = new ArrayList<>();
		
		try {
		      units = unitDao.findAll();
		      correlation = correlationDao.findAllByPrePlanning_IdPrePlanning(userId);
		      likeToKnow = likeToKnowDao.findAllByIdLikeToKnow(userId);
		      plan = planningDao.findById(planId);
		      
		      if(units == null || correlation == null || likeToKnow == null || plan == null) {
		    	  
		    	  return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		      }
		      
		      Date fechaInicio = plan.getStartDate();
		      int duracionSemanas = plan.getDurationWeeks();
		      fechas = calcularFechas(fechaInicio,duracionSemanas);
		      
		      response.put("unit1",units.get(0).getName().toString());
		      response.put("unit2",units.get(1).getName().toString());
		      response.put("unit3",units.get(2).getName().toString());
		      response.put("unit4",units.get(3).getName().toString());
		      response.put("correlacionId", correlation.get(0).getIdCorrelation().toString());
		      response.put("content",correlation.get(0).getConcept());
		      response.put("level",correlation.get(0).getLevel());
		      response.put("description",likeToKnow.get(0).getDescription());
		      
		      responseJson.add(response);
		      responseJson.add(fechas);
		      
		}catch(DataAccessException ex) {
			
			  throw new DataAccessRuntimeException("Error al consultar base de datos");                   //Metodo para devolver datos a la vista crear estrategia 
		}

			return new ResponseEntity<>(responseJson,HttpStatus.OK);  

	}
	
	
	@PostMapping(value="/create")
	public ResponseEntity<MediationStrategies> createPlan(HttpServletRequest request,
			                                   @RequestBody MediationStrategies strategy){
		
		Long userId = getUserIdByToken(request);
		
		MediationStrategies strategySaved;
		List<Correlation> correlationList;
		Correlation correlation = new Correlation();
		
		try {
			
			correlationList = correlationDao.findAllByPrePlanning_IdPrePlanning(userId);
			
			correlation.setIdCorrelation(correlationList.get(0).getIdCorrelation());			
			strategy.setCorrelation(correlation);
		    strategySaved = mediationStrategies.save(strategy);
		
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");                     //Metodo para crear estrategia de mediacion
		} 
		
		if(strategySaved != null) {
			return new ResponseEntity<>(strategySaved,HttpStatus.OK);  
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);  
		}
		
	}
	

	
    public Long getUserIdByToken(HttpServletRequest request) {
    	
    	String header = request.getHeader(StaticSpring.HEADER_STRING);                        
	 
	    return Long.parseLong(tokenService.getId(header));
    }
    
    public String getUserEmailByToken(HttpServletRequest request) {
    	
    	String header = request.getHeader(StaticSpring.HEADER_STRING);                              //Metodo para obtener email de usuario con token
		
		return tokenService.getUsername(header);
    }
    
    public List<String> calcularFechas(Date fechaInicio, int duracionSemanas){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	int dias = 0;
    	List<String> fechas = new ArrayList<>();
    	
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(fechaInicio); 
         switch(duracionSemanas) {
         
         case 1:
        	 dias = 8;
        	 break;
         case 2:
        	 dias = 16;
        	 break;
         case 3:
        	 dias = 24;
        	 break;
         case 4:
        	 dias = 31;
        	 break;
        	 };
        	 
        for(int i = 0; i < dias; i++) {
        	if(calendar.get(Calendar.DAY_OF_WEEK) != 7 && calendar.get(Calendar.DAY_OF_WEEK) != 1) {
        		
        		fechas.add(format.format(calendar.getTime()));
        		
        	}
        	calendar.add(Calendar.DAY_OF_YEAR, 1);
        	
        }
         
        return fechas;
    }
}
