package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name=Medico.FIND_BY_ID, query="SELECT m FROM Medico m WHERE m.id = :" + Medico.ID)
})
public class Medico {
	
	public static final String ID = "id";
	
	public static final String FIND_BY_ID = "Medico.FindById";
	
	@Id
	private int id;
	
	private String nome;
	
	// medico de familia: Medicina Geral e Familiar
	private String especialidade;

	public Medico(){}
	
	public Medico(String nome, String especialidade){
		this.especialidade = especialidade;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}
}
