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

	public void mapFromPoliza(final Poliza poliza){
		id = poliza.getId();
		fechaEmision = poliza.getFecha_emision();
		fechaVencimiento = poliza.getFecha_vencimiento();
		activo = poliza.isActivo();
	}

}

