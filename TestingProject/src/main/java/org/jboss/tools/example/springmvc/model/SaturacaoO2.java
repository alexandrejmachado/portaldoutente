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
	@NamedQuery(name=SaturacaoO2.FIND_ALL_BY_UTENTE, query="SELECT s FROM SaturacaoO2 s WHERE s.numUtente = :" + SaturacaoO2.UTENTE +" ORDER BY s.data ASC")
})
public class SaturacaoO2 {
	
	public static final String UNIDADE = "cm";
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private int id;
	
	private boolean sharable = false;
	
	
	
	public static final String UTENTE = "numUtente";
	
	public static final String FIND_ALL_BY_UTENTE = "SaturacaoO2.findAllByUtente";
	
	@NotNull
	private double valor;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public SaturacaoO2(){}
	
	public SaturacaoO2(double valor, String numUtente){
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
		return this.getValor() + Altura.UNIDADE;
	}
	
	@JsonIgnore
	public boolean isSharable() {
		return sharable;
	}

	@JsonIgnore
	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}
	
}
