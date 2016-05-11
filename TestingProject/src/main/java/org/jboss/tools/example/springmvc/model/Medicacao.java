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
	@NamedQuery(name=Medicacao.FIND_ALL_BY_UTENTE, query="SELECT m FROM Medicacao m WHERE m.numUtente = :" + Medicacao.UTENTE +" ORDER BY m.data DESC")
})
public class Medicacao {

	
	@Id
	@JsonIgnore
	private int id;
	
	public static final String FIND_ALL_BY_UTENTE = "Medicacao.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	@JsonIgnore
	private int numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	private int idMedicamento;
	
	private int dose;
	
	private String indicacoes;
	
	public Medicacao(){}
}
