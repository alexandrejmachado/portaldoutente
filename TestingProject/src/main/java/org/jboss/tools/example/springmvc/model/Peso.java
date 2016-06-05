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

@Entity
@NamedQueries({
	@NamedQuery(name=Peso.FIND_ALL_BY_UTENTE, query="SELECT p FROM Peso p WHERE p.numUtente = :" + Peso.UTENTE +" ORDER BY p.data ASC")
})
public class Peso {

	public static final String UNIDADE = "Kg";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	public static final String UTENTE = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "Peso.findAllByUtente";
	
	@NotNull
	private double valor;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date data;

	public Peso(){}
	
	public Peso(double valor, String numUtente){
		this.valor = valor;
		this.data = new Date();
		this.numUtente = numUtente;
	}
	
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
		return this.getValor() + Peso.UNIDADE;
	}
}
