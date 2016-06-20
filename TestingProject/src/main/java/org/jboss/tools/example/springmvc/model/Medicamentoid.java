package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name=Medicamentoid.FIND_BY_NAME, query="SELECT m.id FROM Medicamentoid m WHERE m.nome = :" + Medicamentoid.NOME)
})
public class Medicamentoid {

	
	@Id
	private int id;
	
	public final static String FIND_BY_NAME = "Medicamentoid.findByName";
	
	public final static String NOME = "nome";
	
	private String nome;
	
	public int getID() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
