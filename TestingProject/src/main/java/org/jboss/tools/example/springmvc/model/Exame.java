package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Exame.FIND_ALL_BY_UTENTE, query="SELECT e FROM Exame e WHERE e.numUtente = :" + Exame.UTENTE +" ORDER BY e.data DESC")
})
public class Exame {

	@Id
	@JsonIgnore
	private int id;
	
	public static final String FIND_ALL_BY_UTENTE = "Exame.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	private boolean feito = false;
	
	@JsonIgnore
	private int numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	private int idMedico;
	
	private String tipo;
	
	@JsonIgnore
	private int idInstituicao;
	
	public Exame(){}
	
}
