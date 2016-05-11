package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name=Medicamento.FIND_ALL, query="SELECT m FROM Medicamento m")
})
public class Medicamento {

	public static final String FIND_ALL = "Medicamento.findAll";
	
	@Id
	private int id;
	
	private String nome;
	
	private String tipo;
	
	public Medicamento(){}
	
}
