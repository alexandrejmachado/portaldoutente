package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Consulta.FIND_ALL_BY_UTENTE, query="SELECT c FROM Consulta c WHERE c.numUtente = :" + Consulta.UTENTE +" ORDER BY c.data DESC")
})
public class Consulta {

	public static final String FIND_ALL_BY_UTENTE = "Consulta.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	@NotNull
	private int idMedico;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@JsonIgnore
	@NotNull
	private int idInstituicao;
	
	
	private boolean feita = false;
	
	public Consulta(){}
	
	public Consulta(int idMedico, String numUtente, int idInstituicao, Date data){
		this.idMedico = idMedico;
		this.numUtente = numUtente;
		this.idInstituicao = idInstituicao;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public String getData() {
		return data.toGMTString();
	}

	public int getIdMedico() {
		return idMedico;
	}

	public String getNumUtente() {
		return numUtente;
	}

	public int getIdInstituicao() {
		return idInstituicao;
	}

	public boolean isFeita() {
		return feita;
	}
	
}
