package com.bbva.IntegradorJava.controllers;

import java.util.List;
import java.util.Optional;

import com.bbva.IntegradorJava.dtos.UserDTO;
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
	public ResponseEntity<List<UserDTO>> findAllUserDTO(){
		return ResponseEntity.ok(us.findAllUserDTO());
		
	}


	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws Exception{
		UserDTO user  = us.buscarUserDTO(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/users")
	public UserDTO createUser(@RequestBody UserDTO userDTO){
		return us.crear(userDTO);
		
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userEditado) throws Exception{
		return ResponseEntity.ok(us.edit(id, userEditado));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deletePoliza(@PathVariable Long id) throws Exception {
	    us.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	

}


