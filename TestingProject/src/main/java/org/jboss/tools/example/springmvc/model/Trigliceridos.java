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
	@NamedQuery(name=Trigliceridos.FIND_ALL_BY_UTENTE, query="SELECT t FROM Trigliceridos t WHERE t.numUtente = :" + Trigliceridos.UTENTE +" ORDER BY t.data DESC")
})
public class Trigliceridos {
	
	public static final String UNIDADE = "cm";
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private int id;
	
	public static final String UTENTE = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "Trigliceridos.findAllByUtente";
	
	@NotNull
	private double valor;
	
	@NotNull
	@JsonIgnore
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Trigliceridos(){}
	
	public Trigliceridos(double valor, String numUtente){
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
	
	public String getNumeroUtente() {
		return numUtente;
	}


	public Date getData() {
		return data;
	}
	
	public String toString(){
		return this.getValor() + Altura.UNIDADE;
	}
	
}
