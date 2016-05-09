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
	@NamedQuery(name=Glicemia.FIND_ALL_BY_UTENTE, query="SELECT g FROM Glicemia g WHERE g.numUtente = :" + Glicemia.UTENTE +" ORDER BY g.data DESC")
})
public class Glicemia {

	public static final String UNIDADE = "mg/dL";
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private int id;
	
	public static final String UTENTE = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "Glicemia.findAllByUtente";
	
	@NotNull
	private double valor;
	
	@NotNull
	@JsonIgnore
	private String numUtente;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Glicemia (){}
	
	public Glicemia(double valor, String numUtente){
		this.valor = valor;
		this.data = new Date();
		this.numUtente = numUtente;
	}

	public static String getUnidade() {
		return UNIDADE;
	}
	
	@JsonIgnore
	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}
	
	@JsonIgnore
	public String getnumeroUtente_() {
		return numUtente;
	}

	@JsonIgnore
	public Date getData_() {
		return data;
	}
	
	public String toString(){
		return this.getValor() + Glicemia.UNIDADE;
	}
	
}
