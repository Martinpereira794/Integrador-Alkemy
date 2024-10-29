package com.bbva.IntegradorJava.controllers;

import java.util.List;
import java.util.Optional;

import com.bbva.IntegradorJava.models.Poliza;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bbva.IntegradorJava.models.User;
import com.bbva.IntegradorJava.services.UserServices;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserServices us;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll(){
		List<User> list = us.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		Optional<User> user  = us.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/users/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User userCreated = us.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
		
	}

	@PutMapping("/user/edit/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userEditado) throws Exception{
		return ResponseEntity.ok(us.edit(id, userEditado));
	}


}


