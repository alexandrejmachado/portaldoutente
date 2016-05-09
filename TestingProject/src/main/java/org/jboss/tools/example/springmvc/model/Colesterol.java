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
//test
@Entity
@NamedQueries({
	@NamedQuery(name=Colesterol.FIND_ALL_BY_UTENTE, query="SELECT c FROM Colesterol c WHERE c.numUtente = :" + Colesterol.Utente +" ORDER BY c.data DESC")
})
public class Colesterol {
	
	public static final String UNIDADE = "cm";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	public static final String FIND_ALL_BY_UTENTE = "Colesterol.findAllByUtente";
	
	public static final String Utente = "numUtente";
	
	@NotNull
	private double valor;
	
	@JsonIgnore
	@NotNull
	private int numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Colesterol(){}
	
	public Colesterol(double valor, int numUtente){
		this.valor = valor;
		this.data = new Date();
		this.numUtente = numUtente;
	}
	
	@JsonIgnore
	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}
	
	@JsonIgnore
	public int getNumeroUtente() {
		return numUtente;
	}


	@JsonIgnore
	public Date getData() {
		return data;
	}
	
	public String toString(){
		return this.getValor() + Altura.UNIDADE;
	}
	
}
