package org.jboss.tools.example.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Vacina.FIND_ALL, query="SELECT v FROM Vacina v")
})
public class Vacina {
	
	@Id
	@JsonIgnore
	private int id;
	
	public static final String FIND_ALL = "Vacina.findAll";
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private String tipo;
	
	private String discricao;
	
	private int idBoletim;
	
	public Vacina(){}

}
