package com.bbva.IntegradorJava.dtos;

import com.bbva.IntegradorJava.models.Seguro;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SeguroDTO {
	
	private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean activo;
    
    
    public SeguroDTO mapFromSeguro(final Seguro seguro) {
    	id = seguro.getId();
    	nombre = seguro.getNombre();
    	descripcion = seguro.getDescripcion();
    	precio = seguro.getPrecio();
    	activo = seguro.isActivo();
    	return this;
    }


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

    
    
	
	
    

     
}
