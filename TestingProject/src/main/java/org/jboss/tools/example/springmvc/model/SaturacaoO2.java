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
	@NamedQuery(name=SaturacaoO2.FIND_ALL_BY_UTENTE, query="SELECT s FROM SaturacaoO2 s WHERE s.clientId = :" + SaturacaoO2.ID +" ORDER BY s.data DESC")
})
public class SaturacaoO2 {
	
	public static final String UNIDADE = "cm";
	
	@Id
	@GeneratedValue
	private int id;
	
	public static final String ID = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "SaturacaoO2.findAllByUtente";
	
	@NotNull
	private double valor;
	
	@NotNull
	private int clientId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public SaturacaoO2(){}
	
	public SaturacaoO2(double valor){
		this.valor = valor;
		this.data = new Date();
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
