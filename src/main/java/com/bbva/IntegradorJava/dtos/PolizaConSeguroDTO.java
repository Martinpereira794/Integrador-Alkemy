package com.bbva.IntegradorJava.dtos;

import com.bbva.IntegradorJava.models.Poliza;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
public class PolizaConSeguroDTO extends PolizaDTO {

    private SeguroDTO seguro;
    
    private UserDTO user;

    @Override
    public PolizaConSeguroDTO mapFromPoliza(final Poliza poliza) {
        super.mapFromPoliza(poliza);

        if (poliza.getSeguro() != null) {
            this.seguro = new SeguroDTO().mapFromSeguro(poliza.getSeguro());
        }
        
        if(poliza.getUser() != null) {
        	this.user = new UserDTO().mapFromUser(poliza.getUser());
        }
        
        return this;
    }

	public SeguroDTO getSeguro() {
		return seguro;
	}

	public void setSeguro(SeguroDTO seguro) {
		this.seguro = seguro;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	
	
    

    
}

