package org.jboss.tools.example.springmvc.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Boletim.FIND_ALL_BY_UTENTE, query="SELECT b FROM Boletim b WHERE b.numUtente = :" + Boletim.UTENTE)
})
public class Boletim {

	public static final String FIND_ALL_BY_UTENTE = "Boletim.findAllByUtente";
	
	public static final String UTENTE = "numUtente";
	
	@Id
	@JsonIgnore
	private int id;
	
	private ArrayList vacinas = new ArrayList();
	
	@JsonIgnore
	private int numUtente;
	
	public Boletim(){}
}
