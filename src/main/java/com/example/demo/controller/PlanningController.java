package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StaticSpring;
import com.example.demo.auth.service.JWTService;
import com.example.demo.model.entity.Planning;
import com.example.demo.model.entity.User;
import com.example.demo.model.error.DataAccessRuntimeException;
import com.example.demo.model.error.UserNotFoundException;
import com.example.demo.model.service.IPlanningService;
import com.example.demo.model.service.IUserService;

@RestController
@RequestMapping(value="/v1/planning")
public class PlanningController {

	@Autowired
	JWTService tokenService;
	
	@Autowired
	IUserService userService;                                                                  //Servicios de token y entidades
	
	@Autowired
	IPlanningService planningService;
	
	@GetMapping(value="/plans")
	public ResponseEntity<List<Planning>> loadPlans(HttpServletRequest request){
		
		List<Planning> planning = null;
		
		Long userId = getUserIdByToken(request);
		
		try {     
			
		    planning = planningService.findAllByUser_UserId(userId);                    //Metodo para cargar todos los planes de un usuario
		
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");
		}
		
		if(!planning.isEmpty()) {
			return new ResponseEntity<>(planning,HttpStatus.OK);  
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping(value="/getPlan/{id}")
	public ResponseEntity<Planning> getPlan(@PathVariable Long id){
		
		Planning plan = null;
		
		try {
			
    		plan = planningService.findById(id);
    		
    		}catch(DataAccessException ex) {
    			throw new DataAccessRuntimeException(ex.getMessage());                  //Metodo para obtener un plan mediante id
    		}
    		
    		if(plan != null) {
    			return new ResponseEntity<>(plan,HttpStatus.OK);  
    		}else {
    			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);  
    		}
	}
	
	@GetMapping(value="/createPlan")
	public ResponseEntity<?> loadUser(HttpServletRequest request){
			
		String email = getUserEmailByToken(request);
		User user = null;
		Map<String, String> response = new HashMap<String, String>();
		
		try {
		      user = userService.findByEmail(email);
		      
		      if(user == null) {
		    	  throw new UserNotFoundException("El usuario no existe");
		      }
		      
		      response.put("regionalDirection",user.getRegionalDirection());
		      response.put("circuit",user.getCircuito());
		      response.put("school",user.getSchool());
		      response.put("levelOfTeaches",user.getLevelOfTeaches());
		      
		}catch(DataAccessException ex) {
			
			  throw new DataAccessRuntimeException("Error al consultar base de datos");                   //Metodo para devolver usuario a la vista crear plan 
		}

			return new ResponseEntity<>(response,HttpStatus.OK);  

	}
	
	@PostMapping(value="/create")
	public ResponseEntity<Planning> createPlan(HttpServletRequest request,
			                                   @RequestBody Planning plan){
		
		User user = new User();
		Long idUser = getUserIdByToken(request);
		
		user.setUserId(idUser);
		plan.setUser(user);
				
		try {
			
		    plan = planningService.save(plan);
		
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");                     //Metodo para crear plan
		} 
		
		if(plan != null) {
			return new ResponseEntity<>(plan,HttpStatus.OK);  
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);  
		}
		
	}
	
	    @PutMapping(value="/update/{id}")		
		public ResponseEntity<Planning> updatePlan(@PathVariable Long id,
				                                    HttpServletRequest request,
				                                   @RequestBody Planning plan){
	    	
			User user = new User();
			Long idUser = getUserIdByToken(request);
			user.setUserId(idUser);
			
			plan.setIdPlan(id);
			plan.setUser(user);
	    	
	    	try {
				
	    		plan = planningService.save(plan);
	    		
	    		}catch(DataAccessException ex) {
	    			throw new DataAccessRuntimeException("Error al consultar base de datos");             //Metodo para actualizar plan
	    		}
	    		
	    		if(plan != null) {
	    			return new ResponseEntity<>(plan,HttpStatus.OK);  
	    		}else {
	    			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);  
	    		}
		    }
	    
	    @DeleteMapping(value="/delete/{id}")
	    public ResponseEntity<String> deletePlan(@PathVariable Long id){
	    	try {
				
	    		planningService.delete(id);
	    		
	    		return new ResponseEntity<>("Borrado con éxito",HttpStatus.OK);
	    		
	    		}catch(DataAccessException ex) {
	    			throw new DataAccessRuntimeException("Error al consultar base de datos");             //Metodo para eliminar plan
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
	   
}
