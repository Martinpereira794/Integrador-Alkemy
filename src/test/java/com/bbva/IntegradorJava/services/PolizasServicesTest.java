package com.bbva.IntegradorJava.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bbva.IntegradorJava.Mocks;
import com.bbva.IntegradorJava.dtos.PolizaConSeguroDTO;
import com.bbva.IntegradorJava.models.Poliza;
import com.bbva.IntegradorJava.models.Seguro;
import com.bbva.IntegradorJava.models.User;
import com.bbva.IntegradorJava.repositories.PolizasRepository;
import com.bbva.IntegradorJava.repositories.SeguroRepository;
import com.bbva.IntegradorJava.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PolizasServicesTest {
	
	@Mock
	private PolizasRepository pr;
	
	@InjectMocks
	private PolizasServices ps;
	
	@Mock
	private SeguroRepository sr;

	@Mock
	private UserRepository ur;

	@Test
	public void testListarTodosConSeguro() {
	    Poliza poliza1 = Mocks.crearPolizaMock(); 
	    Poliza poliza2 = Mocks.crearPolizaMock();
	    List<Poliza> polizas = new ArrayList<>();
	    polizas.add(poliza1);
	    polizas.add(poliza2);
	    
	    Mockito.when(pr.findAll()).thenReturn(polizas);

	    List<PolizaConSeguroDTO> result = ps.listarTodosConSeguro();
	    
	    Assertions.assertThat(result).hasSize(2);
	    
	  
	}
	
	@Test
	public void testBuscarPolizaConSeguroDTOById() throws Exception {
	    Poliza poliza1 = Mocks.crearPolizaMock();
	    Long polizaId = poliza1.getId(); 

	    Mockito.when(pr.findById(polizaId)).thenReturn(Optional.of(poliza1));

	    Optional<PolizaConSeguroDTO> optionalPolizaConSeguroDTO = ps.buscarPolizaConSeguroDTO(polizaId);

	    Assertions.assertThat(optionalPolizaConSeguroDTO).isPresent(); 

	    PolizaConSeguroDTO polizaConSeguroDTO = optionalPolizaConSeguroDTO.get();

	    Assertions.assertThat(polizaConSeguroDTO).isNotNull(); 
	    Assertions.assertThat(polizaConSeguroDTO.getFechaEmision()).isEqualTo(poliza1.getFecha_emision());
	    Assertions.assertThat(polizaConSeguroDTO.getFechaVencimiento()).isEqualTo(poliza1.getFecha_vencimiento());
	    Assertions.assertThat(polizaConSeguroDTO.isActivo()).isEqualTo(poliza1.isActivo());
	    
	    Assertions.assertThat(polizaConSeguroDTO.getSeguro()).isNotNull(); 
	    Assertions.assertThat(polizaConSeguroDTO.getSeguro().getId()).isEqualTo(poliza1.getSeguro().getId());

	    Assertions.assertThat(polizaConSeguroDTO.getUser()).isNotNull(); 
	    Assertions.assertThat(polizaConSeguroDTO.getUser().getId()).isEqualTo(poliza1.getUser().getId());
	}


	
	
	@Test
	public void testEditPoliza() throws Exception {
	    Poliza poliza1 = Mocks.crearPolizaMock();
	    Long polizaId = poliza1.getId();
	    
	    Mockito.when(pr.findById(polizaId)).thenReturn(Optional.of(poliza1));

	    PolizaConSeguroDTO polizaConSeguroDTO = Mocks.crearPolizaConSeguroDTOMock();
	 
	    PolizaConSeguroDTO updatedPolizaDTO = ps.edit(polizaId, polizaConSeguroDTO);

	    Assertions.assertThat(updatedPolizaDTO.getFechaEmision()).isEqualTo(polizaConSeguroDTO.getFechaEmision());
	    Assertions.assertThat(updatedPolizaDTO.getFechaVencimiento()).isEqualTo(polizaConSeguroDTO.getFechaVencimiento());
	    Assertions.assertThat(updatedPolizaDTO.isActivo()).isEqualTo(polizaConSeguroDTO.isActivo());

	    Mockito.verify(pr).save(poliza1);
	}
	
	
	@Test
	public void testBuscarPolizaPorIdNoEncontrado() throws Exception {
	    Long polizaId = 999L; 

	    Mockito.when(pr.findById(polizaId)).thenReturn(Optional.empty());

	    Optional<PolizaConSeguroDTO> optionalPolizaConSeguroDTO = ps.buscarPolizaConSeguroDTO(polizaId);

	    Assertions.assertThat(optionalPolizaConSeguroDTO).isNotPresent(); 
	}

	
	

	 



}
