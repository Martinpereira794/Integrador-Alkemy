package com.bbva.IntegradorJava;

import java.time.LocalDate;
import java.util.Random;

import com.bbva.IntegradorJava.dtos.PolizaConSeguroDTO;
import com.bbva.IntegradorJava.dtos.SeguroDTO;
import com.bbva.IntegradorJava.dtos.UserDTO;
import com.bbva.IntegradorJava.models.Poliza;
import com.bbva.IntegradorJava.models.Seguro;
import com.bbva.IntegradorJava.models.User;

public class Mocks {
	
	 private static Long longRandom(final Long min, final Long max) {
	        return new Random().nextLong(min, max);
	    }
	 
	 public static PolizaConSeguroDTO crearPolizaConSeguroDTOMock() {
		    PolizaConSeguroDTO polizaDTO = new PolizaConSeguroDTO();

		 
		    polizaDTO.setFechaEmision(LocalDate.now());
		    polizaDTO.setFechaVencimiento(LocalDate.now().plusYears(1));
		    polizaDTO.setActivo(true);

		
		    polizaDTO.setSeguro(crearSeguroDTOMock()); 
		    polizaDTO.setUser(crearUserDTOMock());    

		    return polizaDTO;
		}

		public static SeguroDTO crearSeguroDTOMock() {
		    SeguroDTO seguroDTO = new SeguroDTO();
		    seguroDTO.setId(longRandom(1L, 1000000L));
		    seguroDTO.setNombre("Seguro Vida");
		    seguroDTO.setDescripcion("Cobertura completa para accidentes y salud");
		    return seguroDTO;
		}

		public static UserDTO crearUserDTOMock() {
		    UserDTO userDTO = new UserDTO();
		    userDTO.setId(longRandom(1L, 1000000L));
		    userDTO.setNombre("Martin");
		    userDTO.setApellido("Pereira");
		    userDTO.setEmail("martin@example.com");
		    return userDTO;
		}
		
		
		
		
		  public static User crearUserMock() {
		        User user = new User();
		        user.setId(longRandom(1L, 1000L)); // 
		        user.setNombre("Martin");
		        user.setApellido("Pereira");
		        user.setEmail("martin@example.com");
		        user.setUserActivo(true);
		        user.setDni("46909661");
		        user.setPais("Argentina");
		        user.setTelefono("1132017399");
		        user.setUsername("martin");
		        user.setPassword("123");
		        return user;
		    }
		
		 public static Poliza crearPolizaMock() {
		        Poliza poliza = new Poliza();
		        poliza.setId(longRandom(1l, 10000L)); 
		        poliza.setFecha_emision(LocalDate.now()); 
		        poliza.setFecha_vencimiento(LocalDate.now().plusDays(30)); 
		        poliza.setActivo(true); 
		        poliza.setUser(crearUserMock());
		        poliza.setSeguro(crearSeguroMock());
		        
		        return poliza;
		    }
		 
		 public static Seguro crearSeguroMock() {
		        Seguro seguro = new Seguro();
		        seguro.setId(longRandom(1L, 1000L)); 
		        seguro.setNombre("Seguro Vida");
		        seguro.setDescripcion("Cobertura completa para accidentes y salud");
		        seguro.setPrecio(1500);
		        seguro.setActivo(true);
		        return seguro;

		 }
}
