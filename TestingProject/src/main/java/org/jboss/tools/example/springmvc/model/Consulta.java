package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Consulta {

	@Id
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull
	private int idMedico;
	
	@NotNull
	private int idUtente;
	
	@NotNull
	private int idInstituicao;
	
	// string??? ou int???
	private String sala;
	
	private boolean feita = false;
	
	public Consulta(){}
	
}
