package com.bbva.IntegradorJava.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.IntegradorJava.models.Seguro;
import com.bbva.IntegradorJava.services.SeguroServices;

@RestController
@RequestMapping("/api")
public class SeguroController {
	
	
	@Autowired
	private SeguroServices ss;
	
	@GetMapping("/seguros")
	public ResponseEntity<List<Seguro>> findAll(){
		return ResponseEntity.ok(ss.findAll());
	}
	
	@GetMapping("/seguros/{id}")
	public ResponseEntity<Seguro> findById(@PathVariable Long id){
		Optional<Seguro> seguro = ss.findById(id);
        return seguro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}
	
	@PostMapping("/seguros/create")
	public Seguro crearSeguro(@RequestBody Seguro seguro) {
		return ss.save(seguro);
	}
	
	@PutMapping("/seguros/edit/{id}")
	public ResponseEntity<Seguro> edit(@PathVariable Long id, @RequestBody Seguro seguro) throws Exception{
        return ResponseEntity.ok(ss.edit(id, seguro));

	}
	
	
	@DeleteMapping("/seguros/delete/{id}")
	public ResponseEntity<Void> deleteSeguro(@PathVariable Long id){
		ss.delete(id);
		return ResponseEntity.noContent().build();
	}

}
