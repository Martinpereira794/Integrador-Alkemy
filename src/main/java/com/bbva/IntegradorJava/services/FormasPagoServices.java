package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.models.FormasPago;
import com.bbva.IntegradorJava.repositories.FormasPagoRepository;

@Service
public class FormasPagoServices {
	
	@Autowired
	private FormasPagoRepository fpr;
	
	public List<FormasPago> findAll(){
		return fpr.findAll();
		}
	
	public Optional<FormasPago> findById(Long id){
		return fpr.findById(id);
	}
	
	public FormasPago save(FormasPago formaPago)  {
		return fpr.save(formaPago);
	}
	
	public void deleteById(Long id) {
		fpr.deleteById(id);
	}

}
