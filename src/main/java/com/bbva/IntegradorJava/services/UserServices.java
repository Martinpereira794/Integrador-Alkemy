package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;

import com.bbva.IntegradorJava.constants.ErrorConstants;
import com.bbva.IntegradorJava.models.Poliza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.dtos.UserDTO;
import com.bbva.IntegradorJava.models.User;
import com.bbva.IntegradorJava.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository ur;
	
	
	public List<User> findAll() {
		return ur.findAll();	
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
	
	public User crear(UserDTO userDTO) {
		User user = new User();
		user.setNombre(userDTO.getNombre());
		user.setApellido(userDTO.getApellido());
		user.setEmail(userDTO.getEmail());
		return ur.save(user);
	}
	public User edit(Long id, User userEditado) throws Exception {
		Optional<User> userExisting = ur.findById(id);

		if(userExisting.isPresent()) {
			User user = userExisting.get();

			user.setNombre(userEditado.getNombre());
			user.setApellido(userEditado.getApellido());
			user.setEmail(userEditado.getEmail());
			user.setContraseña(userEditado.getContraseña());
			user.setTelefono(userEditado.getTelefono());
			user.setPais(userEditado.getPais());
			user.setDni(userEditado.getDni());
			user.setUserActivo(userEditado.isUserActivo());

			return ur.save(user);
		} else {
			throw new Exception(ErrorConstants.ERROR_USUARIO_NO_ENCONTRADO);
		}
	}

}
	
	
