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
//test
@Entity

@NamedQueries({
	@NamedQuery(name=Altura.FIND_ALL_BY_UTENTE, query="SELECT a FROM Altura a WHERE a.clientId = :" + Altura.ID +" ORDER BY a.data DESC")
})
public class Altura {
	
	public static final String UNIDADE = "cm";
	
	@Id
	@GeneratedValue
	private int id;
	
	public static final String FIND_ALL_BY_UTENTE = "Altura.findAllByUtente";
	
	public static final String ID = "numUtente";
	
	@NotNull
	private double valor;
	
	@NotNull
	private int clientId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Altura(){}
	
	public Altura(double valor, int numUtente){
		this.valor = valor;
		this.data = new Date();
		clientId=numUtente;
	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}
	
	public int getNumeroUtente() {
		return clientId;
	}


	public Date getData() {
		return data;
	}
	
	public String toString(){
		return this.getValor() + Altura.UNIDADE;
	}
	
}
