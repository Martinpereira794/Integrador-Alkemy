package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;

import com.bbva.IntegradorJava.constants.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.models.Poliza;
import com.bbva.IntegradorJava.repositories.PolizasRepository;

@Service
public class PolizasServices {
	
	
	@Autowired
	private PolizasRepository pr;
	
	public List<Poliza> findAll(){
		return pr.findAll();
	}
	
	public Optional<Poliza> findById(Long id) {
		return pr.findById(id);
	}
	
	public Poliza save(Poliza poliza) {
		return pr.save(poliza);
	}
	
	
	
	
	public Poliza edit(Long id, Poliza polizaEditada) throws Exception {
	    Optional<Poliza> polizaExisting = pr.findById(id);
	    
	    if(polizaExisting.isPresent()) {
	        Poliza poliza = polizaExisting.get();
	        
	        poliza.setFecha_emision(polizaEditada.getFecha_emision());
	        poliza.setFecha_vencimiento(polizaEditada.getFecha_vencimiento());
	        poliza.setUser(polizaEditada.getUser());
	        poliza.setSeguro(polizaEditada.getSeguro());
	        poliza.setActivo(polizaEditada.isActivo()); 

	        return pr.save(poliza);
	    } else {
	        throw new Exception(ErrorConstants.ERROR_POLIZA_NO_ENCONTRADA);
	    }
	}
	
	public void deleteById(Long id) throws Exception{
		if(findById(id).isPresent()) {
			pr.deleteById(id);
		} else {
			throw new Exception(ErrorConstants.ERROR_POLIZA_NO_ENCONTRADA);
		}
	}

	
}
	
	
	

