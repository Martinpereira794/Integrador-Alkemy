package com.bbva.IntegradorJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbva.IntegradorJava.models.FormasPago;

@Repository
public interface FormasPagoRepository extends JpaRepository<FormasPago, Long> {
	
	
	public List<FormasPago> findAll();

}
