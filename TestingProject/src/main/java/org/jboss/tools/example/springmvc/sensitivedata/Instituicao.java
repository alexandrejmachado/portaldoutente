package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instituicao {
	
	@Id
	private int id;
	
	private String morada;
	
	private String telefone;
	
	private String email;
	
	private String nome;
	
	private String tipo;
	
	private String localidade;
	
	

	public Instituicao(){}
	
	public Instituicao(String morada,String telefone, String email, String nome, String localidade, String tipo){
		this.morada = morada;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.tipo = tipo;
		this.localidade = localidade;
	}

	public int getId() {
		return id;
	}

	public String getMorada() {
		return morada;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
}
