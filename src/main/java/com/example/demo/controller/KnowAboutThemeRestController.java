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

import com.example.demo.model.entity.KnowAboutTheme;
import com.example.demo.model.service.IKnowAboutThemeService;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/v1")

public class KnowAboutThemeRestController {

	@Autowired(required=true)
	private IKnowAboutThemeService knowAboutThemeService;
	
	@GetMapping("/knowAbout")
	public List<KnowAboutTheme> index(){
		return knowAboutThemeService.findAll();
	}
	
	@GetMapping("/knowAbout/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		KnowAboutTheme knowAbout = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			knowAbout = knowAboutThemeService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "¡Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(knowAbout==null) {
			response.put("mensaje", "¡El #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KnowAboutTheme>(knowAbout, HttpStatus.OK);                                       //Buscar usuario por id
	}
	
	@PostMapping("/knowAbout")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody KnowAboutTheme knowAbout) {                              //Crear usuario
		
		KnowAboutTheme knowAboutNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			knowAboutNew = knowAboutThemeService.save(knowAbout);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Ha sido creado con éxito!");
		response.put("user", knowAboutNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/knowAbout/{id}")
	public ResponseEntity<?> update(@RequestBody KnowAboutTheme knowAbout, @PathVariable Long id) {
		KnowAboutTheme knowAboutActual = knowAboutThemeService.findById(id);
		KnowAboutTheme knowAboutUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(knowAboutActual==null) {
			response.put("mensaje", "¡Error: no se puedo editar, el #: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
	
			knowAboutActual.setDescription(knowAbout.getDescription());
			knowAboutUpdated = knowAboutThemeService.save(knowAboutActual);

	} catch (DataAccessException e) {
		response.put("mensaje", "¡Error al actualizar en la base de datos!");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	response.put("mensaje", "¡Ha sido actualizado con éxito!");
	response.put("KnowAboutTheme", knowAboutUpdated);
	
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
}
	
	@DeleteMapping("/knowAbout/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {                                       //Borrar usuario por id
		Map<String, Object> response = new HashMap<>();
		try {
			knowAboutThemeService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "¡Error al eliminar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡Eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
