package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse.SseBuilder;

import com.bbva.IntegradorJava.constants.ErrorConstants;
import com.bbva.IntegradorJava.dtos.SeguroDTO;
import com.bbva.IntegradorJava.models.Seguro;
import com.bbva.IntegradorJava.repositories.SeguroRepository;

@Service
public class SeguroServices {
	
	@Autowired
	private SeguroRepository sr;
	
	public List<SeguroDTO> findAll() {
	    List<Seguro> list = sr.findAll();
	    return list.stream()
	                .map(seguro -> new SeguroDTO().mapFromSeguro(seguro))
	                .collect(Collectors.toList());
	}

	
	public Optional<SeguroDTO> buscarSeguroDTO(Long id) {
	    return sr.findById(id).map(seguro -> {
	        SeguroDTO seguroDTO = new SeguroDTO();
	        seguroDTO.setId(seguro.getId());
	        seguroDTO.setNombre(seguro.getNombre());
	        seguroDTO.setDescripcion(seguro.getDescripcion());
	        seguroDTO.setPrecio(seguro.getPrecio());
	        seguroDTO.setActivo(seguro.isActivo());
	        return seguroDTO;
	    });
	}


	
	public Optional<Seguro> findById(Long id){
		return sr.findById(id);
	}
	
	public Seguro save(Seguro seguro) {
		return sr.save(seguro);
	}
	
	public void delete(Long id) {
		sr.deleteById(id);
	}
	
	public Seguro edit(Long id, Seguro seguroEditado) throws Exception {
	    Optional<Seguro> seguroExistente = sr.findById(id);

	    if (seguroExistente.isPresent()) {
	        Seguro seguro = seguroExistente.get();

	        seguro.setNombre(seguroEditado.getNombre());
	        seguro.setDescripcion(seguroEditado.getDescripcion());
	        seguro.setPrecio(seguroEditado.getPrecio());
	        seguro.setActivo(seguroEditado.isActivo());

	        return sr.save(seguro);
	    } else {
	        throw new Exception(ErrorConstants.ERROR_SEGURO_NO_ENCONTRADO);
	    }
	}
	
	
}
