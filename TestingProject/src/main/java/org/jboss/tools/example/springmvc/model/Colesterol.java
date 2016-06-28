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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
//test
@Entity
@NamedQueries({
	@NamedQuery(name=Colesterol.FIND_ALL_BY_UTENTE, query="SELECT c FROM Colesterol c WHERE c.numUtente = :" + Colesterol.UTENTE +" ORDER BY c.data ASC"),
	@NamedQuery(name=Colesterol.FIND_SHARABLE, query="SELECT c FROM Colesterol c WHERE c.numUtente = :" + Colesterol.UTENTE +" AND c.sharable = true")
})
public class Colesterol {
	
	public static final String UNIDADE = "cm";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	private boolean sharable = false;
	
	
	public static final String FIND_ALL_BY_UTENTE = "Colesterol.findAllByUtente";
	
	public static final String FIND_SHARABLE = "Colesterol.findShareble";
	
	public static final String UTENTE = "numUtente";
	
	@NotNull
	private double valor;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Colesterol(){}
	
	public Colesterol(double valor, String numUtente){
		this.valor = valor;
		this.data = new Date();
		this.numUtente = numUtente;
	}
	@JsonIgnore
	public boolean isSharable() {
		return sharable;
	}
	@JsonIgnore
	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}
	
	@JsonIgnore
	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}
	
	@JsonIgnore
	public String getNumeroUtente() {
		return numUtente;
	}


	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	public Date getData() {
		return data;
	}
	
	public String toString(){
		return this.getValor() + Altura.UNIDADE;
	}
	
}
