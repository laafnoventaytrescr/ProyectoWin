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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Categorization;
import com.example.demo.model.service.ICategorizationService;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/v1")
public class CategorizationRestController {

	@Autowired(required=true)
	private ICategorizationService iCategorizationService;                           
	
	@GetMapping("/categorization")
	public List<Categorization> index(){
		return iCategorizationService.findAll();                                     
	}
	
	@GetMapping("/categorization/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Categorization categorization = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			categorization = iCategorizationService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "¡Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(categorization==null) {
			response.put("mensaje", "¡La categorizaión #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Categorization>(categorization, HttpStatus.OK);                                       //Buscar usuario por id
	}
	
	@PostMapping("/categorization")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Categorization categorization) {                              //Crear usuario
		
		Categorization categorizationNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categorizationNew = iCategorizationService.save(categorization);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡La categoría ha sido creada con éxito!");
		response.put("user", categorizationNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/categorization/{id}")
	public ResponseEntity<?> update(@RequestBody Categorization categorization, @PathVariable Long id) {
		Categorization categoryActual = iCategorizationService.findById(id);
		Categorization categoryUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(categoryActual==null) {
			response.put("mensaje", "¡Error: no se puedo editar, la categoría #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			categoryActual.setType(categorization.getType());
			categoryUpdated = iCategorizationService.save(categoryActual);

	} catch (DataAccessException e) {
		response.put("mensaje", "¡Error al actualizar la categoría en la base de datos!");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	response.put("mensaje", "¡La categoría ha sido actualizado con éxito!");
	response.put("categorization", categoryUpdated);
	
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
}
	
	@DeleteMapping("/categorization/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {                                       //Borrar usuario por id
		Map<String, Object> response = new HashMap<>();
		try {
			iCategorizationService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al eliminar la categoría en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Categoría eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
