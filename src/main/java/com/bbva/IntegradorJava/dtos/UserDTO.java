package com.bbva.IntegradorJava.dtos;

import com.bbva.IntegradorJava.models.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDTO {

    private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	private String telefono;
	private String password;
	private String username;
	private String pais;

	
	public UserDTO mapFromUser(final User user) {
		
		id = user.getId();
		nombre = user.getNombre();
		apellido = user.getApellido();
		email = user.getEmail();
		dni = user.getEmail();
		telefono = user.getTelefono();
		password = user.getPassword();
		username = user.getUsername();
		pais = user.getPais();
		return this;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

	
	
	
	
	
	
	
}
