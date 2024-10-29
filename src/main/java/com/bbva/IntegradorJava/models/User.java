package com.bbva.IntegradorJava.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    private String nombre;
	
	@NotNull
    private String apellido;
	
	@NotNull
	private String username;
	
	@NotNull
	private String roles;
	
	@NotNull
    private String dni;
	
	@Email(message="El formato no es valido")
    private String email;
	
	@NotNull
    private String telefono;
	
	@NotNull
    private String contraseña;
	
	@NotNull
    private String pais;
	
    private boolean userActivo;

    
    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isUserActivo() {
		return userActivo;
	}

	public void setUserActivo(boolean userActivo) {
		this.userActivo = userActivo;
	}

	public List<Poliza> getPolizasAsociadas() {
		return polizasAsociadas;
	}

	public void setPolizasAsociadas(List<Poliza> polizasAsociadas) {
		this.polizasAsociadas = polizasAsociadas;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Poliza> polizasAsociadas;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    
    }
}


