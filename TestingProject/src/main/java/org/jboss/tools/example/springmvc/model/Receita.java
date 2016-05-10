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
	@NamedQuery(name=Receita.FIND_ALL_BY_UTENTE, query="SELECT r FROM Receita r WHERE r.numUtente = :" + Receita.UTENTE +" ORDER BY r.data DESC")
})
public class Receita {
	
	public static final String FIND_ALL_BY_UTENTE = "Receita.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	@Id
	@JsonIgnore
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	private String numUtente;
	
	private String tipo;
	
	private int idMedicamento;
	
	
	public Receita(){}
}
