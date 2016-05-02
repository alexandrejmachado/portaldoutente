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
	@NamedQuery(name=INR.FIND_ALL_BY_UTENTE, query="SELECT i FROM INR i WHERE i.clientId = :" + INR.ID +" ORDER BY i.data DESC")
})
public class INR {
	
	public static final String UNIDADE = "cm";
	
	@Id
	@GeneratedValue
	private int id;
	
	public static final String ID = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "INR.findAllByUtente";
	
	private double min = 2;
	
	private double max = 3.5;
	
	private boolean alarm;
	@NotNull
	private double valor;
	
	@NotNull
	private int clientId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public INR(){}
	
	public INR(double valor, int numUtente){
		this.valor = valor;
		this.data = new Date();
		this.clientId = numUtente;
		if (valor<2 || valor>3.5) {
			alarm = true;
		}
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
