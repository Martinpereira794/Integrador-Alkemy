package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.constants.ErrorConstants;
import com.bbva.IntegradorJava.dtos.UserDTO;
import com.bbva.IntegradorJava.models.User;
import com.bbva.IntegradorJava.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<User> findAll() {
		return ur.findAll();	
	}
	
	public List<UserDTO> findAllUserDTO(){
		List<User> list = ur.findAll();
		return list.stream().map(user -> new UserDTO().mapFromUser(user))
				.collect(Collectors.toList());
		
	}
	
	public User getByUsername(String username){
		return ur.findByUsername(username);
	}
	
	
	public Optional<User> findById(Long id){
		return ur.findById(id);
	}
	
	public User save(User user) {
		return ur.save(user);
	}
	
	public void deleteById(Long id) {
		ur.deleteById(id);
	}
	
	@Transactional
	public UserDTO crear(UserDTO userDTO) {
	    User user = new User();
	    user.setNombre(userDTO.getNombre());
	    user.setApellido(userDTO.getApellido());
	    user.setEmail(userDTO.getEmail());
	    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	    user.setPais(userDTO.getPais());
	    user.setTelefono(userDTO.getTelefono());
	    user.setDni(userDTO.getDni());
	    user.setUsername(userDTO.getUsername());
	    
	    // Guardar el usuario en la base de datos
	    user = ur.save(user);
	    
	    // Mapear y devolver el UserDTO
	    return new UserDTO().mapFromUser(user);
	}
	
	public User edit(Long id, User userEditado) throws Exception {
		Optional<User> userExisting = ur.findById(id);

		if(userExisting.isPresent()) {
			User user = userExisting.get();

			user.setNombre(userEditado.getNombre());
			user.setApellido(userEditado.getApellido());
			user.setEmail(userEditado.getEmail());
			user.setPassword(userEditado.getPassword());
			user.setTelefono(userEditado.getTelefono());
			user.setPais(userEditado.getPais());
			user.setDni(userEditado.getDni());
			user.setUserActivo(userEditado.isUserActivo());

			return ur.save(user);
		} else {
			throw new Exception(ErrorConstants.ERROR_USUARIO_NO_ENCONTRADO);
		}
		
	}
	
	public UserDTO buscarUserDTO(Long id) throws Exception {
		Optional<User> userOpt = ur.findById(id);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			UserDTO userDto = new UserDTO();
			userDto.setId(user.getId());
			userDto.setNombre(user.getNombre());
			userDto.setApellido(user.getApellido());
			userDto.setEmail(user.getEmail());
			userDto.setDni(user.getDni());
			userDto.setTelefono(user.getTelefono());
			userDto.setPassword(user.getPassword());
			userDto.setUsername(user.getUsername());
			userDto.setPais(user.getPais());
			return userDto;
			
		} else {
			throw new Exception(ErrorConstants.ERROR_USUARIO_NO_ENCONTRADO);
		}
		
	}

}
	
	
