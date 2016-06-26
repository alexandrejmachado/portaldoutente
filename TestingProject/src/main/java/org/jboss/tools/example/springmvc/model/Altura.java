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
	@NamedQuery(name=Altura.FIND_ALL_BY_UTENTE, query="SELECT a FROM Altura a WHERE a.numUtente = :" + Altura.UTENTE +" ORDER BY a.data ASC")
})
public class Altura {
	
	public static final String UNIDADE = "cm";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	public static final String FIND_ALL_BY_UTENTE = "Altura.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	@NotNull
	private double valor;
	
	private boolean sharable = false;
	

	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Altura(){}
	
	public Altura(double valor, String numUtente){
		this.valor = valor;
		this.data = new Date();
		this.numUtente=numUtente;
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
		return this.getValor() + Altura.UNIDADE;
	}
	
	public boolean isSharable() {
		return sharable;
	}

	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}
	
}
