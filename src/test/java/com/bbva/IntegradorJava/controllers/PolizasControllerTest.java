package com.bbva.IntegradorJava.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bbva.IntegradorJava.Mocks;
import com.bbva.IntegradorJava.dtos.PolizaConSeguroDTO;
import com.bbva.IntegradorJava.exceptions.ExceptionsTP;
import com.bbva.IntegradorJava.repositories.PolizasRepository;
import com.bbva.IntegradorJava.repositories.SeguroRepository;
import com.bbva.IntegradorJava.services.PolizasServices;

@ExtendWith(MockitoExtension.class)
public class PolizasControllerTest {

	@Mock
	private PolizasRepository pr;
	
	  @Mock
	    private SeguroRepository sr;

	
	@InjectMocks
	private PolizasController pc;
	
	@Mock
	private PolizasServices ps;
	
	
	private AutoCloseable ac;
	
	@BeforeEach
	void setUp() {
		System.out.println("Este es el beforeEach");
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Este es el afterEach");
		ac.close();
	}
	
	@BeforeAll
    static void beforeAll() {
        System.out.println("Before ALL");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AFTER ALL");
    }
    
    
    @Test
    void testPolizaBuscarPorId() throws Exception {  
        System.out.println("Esta es la ejecución de la Prueba Unitaria BUSCAR POLIZA POR ID");

        PolizaConSeguroDTO polizaDTO = Mocks.crearPolizaConSeguroDTOMock();
        Long id = polizaDTO.getId(); 

        Mockito.when(ps.buscarPolizaConSeguroDTO(id))
               .thenReturn(Optional.of(polizaDTO)); 

        ResponseEntity<PolizaConSeguroDTO> response = pc.obtenerPolizaConSeguroDTO(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(polizaDTO, response.getBody());
    }

    
    @Test
    void testPolizaBuscarPorId_NotFound() throws Exception {
        Long id = 1000L; 
        Mockito.when(ps.buscarPolizaConSeguroDTO(id)).thenReturn(Optional.empty());

        ResponseEntity<PolizaConSeguroDTO> response = pc.obtenerPolizaConSeguroDTO(id);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


   
    
    @Test
    void testListarPolizas() {
        System.out.println("Esta es la ejecución de la Prueba Unitaria LISTAR TODOS CON SEGURO");

        List<PolizaConSeguroDTO> polizasMock = Arrays.asList(
        	    Mocks.crearPolizaConSeguroDTOMock(),
        	    Mocks.crearPolizaConSeguroDTOMock()
        	);

        Mockito.when(ps.listarTodosConSeguro()).thenReturn(polizasMock);

        ResponseEntity<List<PolizaConSeguroDTO>> response = pc.listarTodosConSeguro();

        Assertions.assertNotNull(response); 
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); 
        Assertions.assertEquals(polizasMock.size(), response.getBody().size()); 
        Assertions.assertEquals(polizasMock, response.getBody()); 
    }
    
    
    @Test
    void testListarPolizasEmpty() {
        System.out.println("Esta es la ejecución de la Prueba Unitaria LISTAR TODOS CON SEGURO - VACÍO");

        List<PolizaConSeguroDTO> polizasMock = Collections.emptyList(); 

        Mockito.when(ps.listarTodosConSeguro()).thenReturn(polizasMock);

        ResponseEntity<List<PolizaConSeguroDTO>> response = pc.listarTodosConSeguro();

        Assertions.assertNotNull(response); 
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); 
        Assertions.assertEquals(0, response.getBody().size()); 
    }
    
    @Test
    void testCreatePolizaConSeguroDTO() {
        System.out.println("Esta es la ejecución de la Prueba Unitaria CREAR POLIZA");

        PolizaConSeguroDTO polizaMock = Mocks.crearPolizaConSeguroDTOMock();

        Mockito.when(ps.create(ArgumentMatchers.any(PolizaConSeguroDTO.class))).thenReturn(polizaMock);

       PolizaConSeguroDTO response = pc.createPolizaConSeguroDTO(polizaMock);
        // 
       Assertions.assertEquals(polizaMock.getId(), response.getId());
       Assertions.assertEquals(polizaMock.getFechaEmision(), response.getFechaEmision());
       Assertions.assertEquals(polizaMock.getFechaVencimiento(), response.getFechaVencimiento());
       Assertions.assertEquals(polizaMock.isActivo(), response.isActivo());
       Assertions.assertEquals(polizaMock.getUser(), response.getUser());
       Assertions.assertEquals(polizaMock.getSeguro(), response.getSeguro());
       
    }
    
    @Test
    void testCreatePolizaConSeguroDTO_Fail() {
        System.out.println("Esta es la ejecución de la Prueba Unitaria CREAR POLIZA - FALTA ALGO");

        PolizaConSeguroDTO polizaMock = Mocks.crearPolizaConSeguroDTOMock();

        Mockito.when(ps.create(ArgumentMatchers.any(PolizaConSeguroDTO.class)))
               .thenThrow(new ExceptionsTP(HttpStatus.BAD_REQUEST, "Error al crear la póliza"));

        Exception exception = Assertions.assertThrows(ExceptionsTP.class, () -> {
            pc.createPolizaConSeguroDTO(polizaMock);
        });

        Assertions.assertEquals("Error al crear la póliza", exception.getMessage());
    }


    
    @Test
    void testDeletePoliza() throws Exception {
        Long polizaId = 1L;
        ResponseEntity<Void> response = pc.deletePoliza(polizaId);
        Mockito.verify(ps).deleteById(polizaId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
    @Test
    void testDeletePolizaDontExist() throws Exception {
        Long polizaId = 1000L;

        Mockito.doThrow(new ExceptionsTP(HttpStatus.NOT_FOUND, "El recurso solicitado no existe"))
               .when(ps).deleteById(polizaId);
        ResponseEntity<Void> response = pc.deletePoliza(polizaId);

        Mockito.verify(ps).deleteById(polizaId);
        
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    

    
  

    
	
}

