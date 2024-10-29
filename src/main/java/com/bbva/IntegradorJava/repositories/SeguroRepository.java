package com.bbva.IntegradorJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbva.IntegradorJava.models.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long>{

	public List<Seguro> findAll();
}
