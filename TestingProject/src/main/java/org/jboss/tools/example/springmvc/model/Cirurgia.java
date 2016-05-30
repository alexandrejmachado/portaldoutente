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
	@NamedQuery(name=Cirurgia.FIND_ALL, query="SELECT c FROM Cirurgia c"),
	@NamedQuery(name=Cirurgia.FIND_ALL_BY_UTENTE, query="SELECT c FROM Cirurgia c WHERE c.numUtente = :" + Cirurgia.UTENTE +" ORDER BY c.data DESC"),
	@NamedQuery(name=Cirurgia.FIND_ALL_BY_MEDICO, query="SELECT c FROM Cirurgia c WHERE c.idMedico = :" + Cirurgia.MEDICO +" ORDER BY c.data DESC")
})
public class Cirurgia {

	@Id
	@JsonIgnore
	private int id;
	
	public static final String FIND_ALL = "Cirurgia.findAll";
	public static final String FIND_ALL_BY_UTENTE = "Cirurgia.findAllByUtente";
	public static final String FIND_ALL_BY_MEDICO = "Cirurgia.findAllByMedico";
	
	public static final String UTENTE = "numUtente";
	public static final String MEDICO = "idMedico";
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private String tipo;
	
	@JsonIgnore
	private int idMedico;
	
	@JsonIgnore
	private int numUtente;
	
	@JsonIgnore
	private boolean pendente= false;
	
	@JsonIgnore
	public Cirurgia(){}
	
	public Cirurgia(int numUtente, int idMedico, String tipo) {
		this.numUtente = numUtente;
		this.idMedico = idMedico;
		this.tipo = tipo;
		data = new Date();
	}
	
	public boolean confirmar() {
		pendente= true;
		return true;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public int getNumUtente() {
		return numUtente;
	}

	public void setNumUtente(int numUtente) {
		this.numUtente = numUtente;
	}

	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}
	
	
	
	
	
}
