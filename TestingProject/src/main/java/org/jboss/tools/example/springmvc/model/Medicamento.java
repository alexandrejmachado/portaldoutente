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
	
	private double doseDiaria;
	
	
	
	public Medicamento(){}
	
	
	
	
}
