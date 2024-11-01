package com.bbva.IntegradorJava.dtos;

import com.bbva.IntegradorJava.models.Poliza;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PolizaDTO {

	private Long id;
	private LocalDate fechaEmision;
	private LocalDate fechaVencimiento;
	private boolean activo;

	public PolizaDTO mapFromPoliza(final Poliza poliza){
		id = poliza.getId();
		fechaEmision = poliza.getFecha_emision();
		fechaVencimiento = poliza.getFecha_vencimiento();
		activo = poliza.isActivo();
		return this;
	}
	
	

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

