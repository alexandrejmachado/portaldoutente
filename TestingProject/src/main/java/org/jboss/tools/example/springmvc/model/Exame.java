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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Exame.FIND_ALL_BY_UTENTE, query="SELECT e FROM Exame e WHERE e.numUtente = :" + Exame.UTENTE +" ORDER BY e.data DESC"),
	@NamedQuery(name=Exame.FIND_ALL_BY_MEDICO, query="SELECT e FROM Exame e WHERE e.idMedico = :" + Exame.MEDICO + " ORDER BY e.data DESC")
})
public class Exame {

	@Id
	@JsonIgnore
	@GeneratedValue
	private int id;
	
	private boolean sharable = false;
	
	
	
	public static final String FIND_ALL_BY_UTENTE = "Exame.findAllByUtente";
	
	public static final String FIND_ALL_BY_MEDICO = "Exame.findAllByMedico";
	
	public static final String UTENTE = "numUtente";
	
	public static final String MEDICO = "idMedico";
	
	private boolean feito = false;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	private int idMedico;
	
	private String tipo;
	
	@JsonIgnore
	private int idInstituicao;
	
	@NotNull
	private String metalink;
	
	private String nome;
	
	private String nomeUtente;
	
	public Exame(){}
	
	public Exame(String numUtente,Date date,String metalink, String tipo, String nomeUtente, int idMedico)
	{
		this.idMedico = idMedico;
		this.numUtente=numUtente;
		this.data=date;
		this.metalink=metalink;
		this.nome = metalink.split("/")[1];
		System.out.println(nome);
		this.tipo = tipo;
		this.setNomeUtente(nomeUtente);
		System.out.println(tipo);
	}
	
	public String getNumUtente(){return numUtente;}
	
	public String getMetalink(){return metalink;}
	
	public Date getDate() {return data;}
	
	public String getTipo() {return tipo;}
	
	public String getNome() {return nome;}
	
	public boolean isSharable() {
		return sharable;
	}

	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
}
