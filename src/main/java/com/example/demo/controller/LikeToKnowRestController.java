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

import com.example.demo.model.entity.LikeToKnow;
import com.example.demo.model.service.ILikeToKnowService;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/v1")

public class LikeToKnowRestController {
	
	@Autowired(required=true)
	private ILikeToKnowService iLikeToKnowService;
	
	@GetMapping("/toKnowList/{id}")
	public List<LikeToKnow> index(@PathVariable Long id){
		return iLikeToKnowService.findAllByIdLikeToKnow(id);
	}
	
	@GetMapping("/toKnow/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		LikeToKnow toKnow = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			toKnow = iLikeToKnowService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "¡Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(toKnow==null) {
			response.put("mensaje", "¡El #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LikeToKnow>(toKnow, HttpStatus.OK);                                       //Buscar likeToKnow por id
	}
	
	@PostMapping("/toKnow")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody LikeToKnow toKnow) {                              //Crear likeToKnow
		
		LikeToKnow toKnowNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			toKnowNew = iLikeToKnowService.save(toKnow);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡El insert ha sido realizado con éxito!");
		response.put("toKnow", toKnowNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/toKnow/{id}")
	public ResponseEntity<?> update(@RequestBody LikeToKnow likeToKnow, @PathVariable Long id) {
		LikeToKnow toKnowActual = iLikeToKnowService.findById(id);
		LikeToKnow toKnowUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(toKnowActual==null) {
			response.put("mensaje", "¡Error: no se puedo editar, el #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			toKnowActual.setDescription(likeToKnow.getDescription());
			toKnowUpdated = iLikeToKnowService.save(toKnowActual);

	} catch (DataAccessException e) {
		response.put("mensaje", "¡Error al actualizar en la base de datos!");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	response.put("mensaje", "¡Ha sido actualizado con éxito!");
	response.put("toKnow", toKnowUpdated);
	
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
}
	
	@DeleteMapping("/toKnow/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {                                       //Borrar likeToKnow por id
		Map<String, Object> response = new HashMap<>();
		try {
			iLikeToKnowService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al eliminar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}


}
