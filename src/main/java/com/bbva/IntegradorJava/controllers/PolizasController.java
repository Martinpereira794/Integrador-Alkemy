package com.bbva.IntegradorJava.controllers;

import java.util.List;
import java.util.Optional;

import com.bbva.IntegradorJava.dtos.PolizaConSeguroDTO;
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

import com.bbva.IntegradorJava.services.PolizasServices;

@RestController
@RequestMapping("/api")
public class PolizasController {
    
    @Autowired
    private PolizasServices ps;

    // Métodos GET
    @GetMapping("/polizas/{id}")
    public ResponseEntity<PolizaConSeguroDTO> obtenerPolizaConSeguroDTO(@PathVariable Long id) throws Exception {
        Optional<PolizaConSeguroDTO> polizaConSeguroDTOOpt = ps.buscarPolizaConSeguroDTO(id);
        
        return polizaConSeguroDTOOpt
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/polizas")
    public ResponseEntity<List<PolizaConSeguroDTO>> listarTodosConSeguro(){
        return ResponseEntity.ok(ps.listarTodosConSeguro());
    }

    // Métodos POST
    @PostMapping("/polizas")
    public PolizaConSeguroDTO createPolizaConSeguroDTO(@RequestBody PolizaConSeguroDTO polizaConSeguroDTO) {
        return ps.create(polizaConSeguroDTO);
    }

    // Métodos PUT
    @PutMapping("/polizas/{id}")
    public ResponseEntity<PolizaConSeguroDTO> updatePoliza(@PathVariable Long id, @RequestBody PolizaConSeguroDTO polizaConSeguroDTO) throws Exception {
        PolizaConSeguroDTO updatedPoliza = ps.edit(id, polizaConSeguroDTO);
        return ResponseEntity.ok(updatedPoliza);
    }
    
    // Métodos DELETE
    @DeleteMapping("/polizas/{id}")
    public ResponseEntity<Void> deletePoliza(@PathVariable Long id) throws Exception {
        ps.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
