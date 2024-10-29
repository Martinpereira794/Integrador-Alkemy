package com.bbva.IntegradorJava.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bbva.IntegradorJava.models.Poliza;
import com.bbva.IntegradorJava.services.PolizasServices;

@RestController
@RequestMapping("/api")
public class PolizasController {
	
	@Autowired
	private PolizasServices ps;
	
	@GetMapping("/poliza")
	public ResponseEntity<List<Poliza>> getAll() {
		List<Poliza> list = ps.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/poliza/{id}")
	public ResponseEntity<Poliza> findById(@PathVariable Long id){
		Optional<Poliza> poliza = ps.findById(id);
        return poliza.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}
	
	@PostMapping("/poliza/create")
	public ResponseEntity<Poliza> createPoliza(@RequestBody Poliza poliza){
		Poliza polizaCreated = ps.save(poliza);
		return ResponseEntity.status(HttpStatus.CREATED).body(polizaCreated);
		
	}
	
	
	
	
	
	@PutMapping("/poliza/edit/{id}")
	public ResponseEntity<Poliza> updatePoliza(@PathVariable Long id, @RequestBody Poliza polizaEdited) throws Exception {
	    return ResponseEntity.ok(ps.edit(id, polizaEdited));
	    
	}
	
	
	
	
	@DeleteMapping("/poliza/delete/{id}")
	public ResponseEntity<Poliza> deletePoliza(@PathVariable Long id) throws Exception{
		ps.deleteById(id);
		return ResponseEntity.noContent().build();
	}
 
}
