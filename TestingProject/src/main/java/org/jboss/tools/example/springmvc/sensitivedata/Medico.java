package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
public class Medico {
	
	@Id
	private int id;
	
	private String nome;
	
	private String especialidade;

	public Medico(){}
}
