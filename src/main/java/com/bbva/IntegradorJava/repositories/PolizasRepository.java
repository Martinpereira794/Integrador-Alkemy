package com.bbva.IntegradorJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbva.IntegradorJava.models.Poliza;

@Repository
public interface PolizasRepository extends JpaRepository<Poliza, Long> {

	
	List<Poliza> findAll();
}
