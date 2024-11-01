package com.bbva.IntegradorJava.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.dtos.PolizaConSeguroDTO;
import com.bbva.IntegradorJava.dtos.PolizaDTO;
import com.bbva.IntegradorJava.dtos.SeguroDTO;
import com.bbva.IntegradorJava.dtos.UserDTO;
import com.bbva.IntegradorJava.exceptions.ExceptionsTP;
import com.bbva.IntegradorJava.models.Poliza;
import com.bbva.IntegradorJava.models.Seguro;
import com.bbva.IntegradorJava.models.User;
import com.bbva.IntegradorJava.repositories.PolizasRepository;
import com.bbva.IntegradorJava.repositories.SeguroRepository;
import com.bbva.IntegradorJava.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PolizasServices {
    
    @Autowired
    private SeguroRepository sr;
    
    @Autowired
    private UserRepository ur;
    
    @Autowired
    private PolizasRepository pr;
    
    // Métodos de Listado
    public List<PolizaDTO> listarTodos() {
        List<Poliza> list = pr.findAll();
        return list.stream()
                   .map(poliza -> new PolizaDTO().mapFromPoliza(poliza))
                   .collect(Collectors.toList());
    }
    
    public List<PolizaConSeguroDTO> listarTodosConSeguro() {
        List<Poliza> list = pr.findAll();
        return list.stream()
                   .map(poliza -> new PolizaConSeguroDTO().mapFromPoliza(poliza))
                   .collect(Collectors.toList());
    }
    
    // Métodos de Creación y Edición
    @Transactional
    public PolizaConSeguroDTO create(PolizaConSeguroDTO polizaDTO) {
        Poliza poliza = new Poliza();
        
        poliza.setFecha_emision(polizaDTO.getFechaEmision());
        poliza.setFecha_vencimiento(polizaDTO.getFechaVencimiento());
        poliza.setActivo(Boolean.TRUE);

        if (polizaDTO.getSeguro() != null && polizaDTO.getSeguro().getId() != null) {
            Optional<Seguro> seguroOpt = sr.findById(polizaDTO.getSeguro().getId());
            if (seguroOpt.isPresent()) {
                poliza.setSeguro(seguroOpt.get());
            } else {
                throw new ExceptionsTP(HttpStatus.NOT_FOUND, "El recurso solicitado no existe");
            }
        }

        if (polizaDTO.getUser() != null && polizaDTO.getUser().getId() != null) {
            Optional<User> userOpt = ur.findById(polizaDTO.getUser().getId());
            if (userOpt.isPresent()) {
                poliza.setUser(userOpt.get());
            } else {
                throw new ExceptionsTP(HttpStatus.NOT_FOUND, "El recurso solicitado no existe");
            }
        }

        Poliza savedPoliza = pr.save(poliza);
        
        return new PolizaConSeguroDTO().mapFromPoliza(savedPoliza);
    }

    @Transactional
    public PolizaConSeguroDTO edit(Long id, PolizaConSeguroDTO polizaConSeguroDTO) throws Exception {
        Optional<Poliza> polizaExisting = pr.findById(id);

        if (polizaExisting.isPresent()) {
            Poliza poliza = polizaExisting.get();

            poliza.setFecha_emision(polizaConSeguroDTO.getFechaEmision());
            poliza.setFecha_vencimiento(polizaConSeguroDTO.getFechaVencimiento());
            poliza.setActivo(polizaConSeguroDTO.isActivo());
            poliza.setUser(poliza.getUser());
            poliza.setSeguro(poliza.getSeguro());
           
            pr.save(poliza);
            
            return new PolizaConSeguroDTO().mapFromPoliza(poliza);
        } else {
            throw new ExceptionsTP(HttpStatus.NOT_FOUND, "El recurso solicitado no existe");
        }
    }
    
    // Métodos de Búsqueda
    public Optional<PolizaDTO> buscarPolizaDTO(Long id) {
        return pr.findById(id).map(poliza -> {
            PolizaDTO polizaDTO = new PolizaDTO();
            polizaDTO.setFechaEmision(poliza.getFecha_emision());
            polizaDTO.setFechaVencimiento(poliza.getFecha_vencimiento());
            polizaDTO.setActivo(poliza.isActivo());
            return polizaDTO;
        });
    }

    public Optional<PolizaConSeguroDTO> buscarPolizaConSeguroDTO(Long id) throws Exception {
        return pr.findById(id).map(poliza -> {
            PolizaConSeguroDTO polizaConSeguroDTO = new PolizaConSeguroDTO();
            polizaConSeguroDTO.setFechaEmision(poliza.getFecha_emision());
            polizaConSeguroDTO.setFechaVencimiento(poliza.getFecha_vencimiento());
            polizaConSeguroDTO.setActivo(poliza.isActivo());

            if (poliza.getSeguro() != null) {
                SeguroDTO seguroDTO = new SeguroDTO().mapFromSeguro(poliza.getSeguro());
                polizaConSeguroDTO.setSeguro(seguroDTO);
            }

            if (poliza.getUser() != null) {
                UserDTO userDTO = new UserDTO().mapFromUser(poliza.getUser());
                polizaConSeguroDTO.setUser(userDTO);
            }

            return polizaConSeguroDTO;
        });
    }

    public Optional<Poliza> findById(Long id){
        return pr.findById(id);
    }
    
    // Métodos de Eliminación
    public void deleteById(Long id) throws Exception {
        if (pr.findById(id).isPresent()) {
            pr.deleteById(id);
        } else {
            throw new ExceptionsTP(HttpStatus.NOT_FOUND, "El recurso solicitado no existe");
        }
    }
    
    // Métodos de Persistencia
    public Poliza save(Poliza poliza) {
        return pr.save(poliza);
    }
}
