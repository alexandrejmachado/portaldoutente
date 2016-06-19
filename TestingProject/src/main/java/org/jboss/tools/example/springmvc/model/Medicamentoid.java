package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medicamentoid {

	
	@Id
	private int id;
	
	private String nome;
	
	private int getID() {
		return id;
	}
	
	private String getNome() {
		return nome;
	}
	
	private void setID(int id) {
		this.id = id;
	}
	
	private void setNome(String nome) {
		this.nome = nome;
	}
}
