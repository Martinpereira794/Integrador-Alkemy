package com.bbva.IntegradorJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbva.IntegradorJava.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findAll();
	
	User findByUsername(String username);
}
