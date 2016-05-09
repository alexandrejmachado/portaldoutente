package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Consulta.FIND_ALL_BY_UTENTE, query="SELECT c FROM Consulta c WHERE c.numUtente = :" + Consulta.Utente +" ORDER BY c.data DESC")
})
public class Consulta {

	public static final String FIND_ALL_BY_UTENTE = "Consulta.findAllByUtente";
	
	public static final String Utente = "numUtente";
	
	@JsonIgnore
	@Id
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	@NotNull
	private int idMedico;
	
	@JsonIgnore
	@NotNull
	private int numUtente;
	
	@JsonIgnore
	@NotNull
	private int idInstituicao;
	
	// string??? ou int???
	private String sala;
	
	private boolean feita = false;
	
	public Consulta(){
		//TODO buscar os nomes do medico instituicao etc
	}
	
}
