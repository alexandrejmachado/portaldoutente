package org.jboss.tools.example.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public enum EstadoRenovacao {

	PENDENTE,ACEITE,CADUCADO,REJEITADO
	
}
