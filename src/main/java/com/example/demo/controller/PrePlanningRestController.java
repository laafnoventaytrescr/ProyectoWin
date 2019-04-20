package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.PrePlanning;
import com.example.demo.model.service.IPrePlanningService;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/v1")
public class PrePlanningRestController {
	
	@Autowired(required=true)
	private IPrePlanningService prePlanningService;                           
	
	@GetMapping("/preplanning")
	public List<PrePlanning> index(){
		return prePlanningService.findAll();                                     
	}
	
	@GetMapping("/preplanning/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		PrePlanning planning = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			planning = prePlanningService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "¡Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(planning==null) {
			response.put("mensaje", "¡El #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PrePlanning>(planning, HttpStatus.OK);                                       //Buscar preplanning por id
	}
	
	@PostMapping("/preplanning")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody PrePlanning planning) {                              //Crear preplanning
		
		PrePlanning planningNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			planningNew = prePlanningService.save(planning);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Ha sido creado con éxito!");
		response.put("user", planningNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/preplanning/{id}")
	public ResponseEntity<?> update(@RequestBody PrePlanning planning, @PathVariable Long id) {
		PrePlanning planningActual = prePlanningService.findById(id);
		PrePlanning planningUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(planningActual==null) {
			response.put("mensaje", "¡Error: no se puedo editar, el usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			planningActual.setMainLikeToKnow(planning.getMainLikeToKnow());
			planningActual.setIdKnowAboutTheme(planning.getIdKnowAboutTheme());
			planningActual.setIdLikeToKnow(planning.getIdLikeToKnow());
			planningActual.setIdCategorization(planning.getIdCategorization());
			planningUpdated = prePlanningService.save(planningActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al actualizar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡Ha sido actualizado con éxito!");
		response.put("user", planningUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/preplanning/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {                                       //Borrar preplanning por id
		Map<String, Object> response = new HashMap<>();
		try {
			prePlanningService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al eliminar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	
	}
	
	
	
	////////////////////////////////////PrePlanning Preview//////////////////////////////////////////////

	 
	

}
