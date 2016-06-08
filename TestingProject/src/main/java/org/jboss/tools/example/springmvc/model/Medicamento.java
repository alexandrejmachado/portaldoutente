package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name=Medicamento.FIND_ALL, query="SELECT m FROM Medicamento m"),
	@NamedQuery(name=Medicamento.FIND_BY_NAME, query="SELECT m FROM Medicamento m WHERE m.nome = :" + Medicamento.NOME)
})
public class Medicamento {

	public static final String FIND_ALL = "Medicamento.findAll";
	
	public static final String FIND_BY_NAME = "Medicamento.findByName";
	
	public static final String NOME = "nome";
	
	@Id
	private int id;
	
	private String nome;
	
	private String tipo;
	
	private int comprimidosPorCaixa;
	
	
	
	public Medicamento(){}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getComprimidos() {
		return comprimidosPorCaixa;
	}



	public void setComprimidos(int comprimidosPorCaixa) {
		this.comprimidosPorCaixa = comprimidosPorCaixa;
	}
	
	
	
	
	
	
}
