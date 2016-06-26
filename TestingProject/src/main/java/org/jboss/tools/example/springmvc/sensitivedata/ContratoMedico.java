package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name=ContratoMedico.FIND_BY_CENTRO, query="SELECT cm FROM ContratoMedico cm WHERE cm.centroId = :"+ContratoMedico.CENTRO_ID),
	@NamedQuery(name=ContratoMedico.FIND_BY_MEDICO, query="SELECT cm FROM ContratoMedico cm WHERE cm.centroId = :"+ContratoMedico.CENTRO_ID)
})
public class ContratoMedico {

	public static final String FIND_BY_CENTRO = "ContratoMedico.FindByCentro";
	
	public static final String FIND_BY_MEDICO = "ContratoMedico.FindByMedico";
	
	public static final String CENTRO_ID = "centroId";
	
	public static final String MEDICO_ID = "medicoId";
	
	@Id
	private int id;
	
	private int medicoId;
	
	private int centroId;
	
	public ContratoMedico(){}
	
	public ContratoMedico(int medicoId, int centroId){
		this.medicoId = medicoId;
		this.centroId = centroId;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public int getCentroId() {
		return centroId;
	}
	
	
}
