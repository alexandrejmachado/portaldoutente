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

import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Consulta.FIND_ALL, query="SELECT c FROM Consulta c"),
	@NamedQuery(name=Consulta.FIND_BY_ID, query="SELECT c FROM Consulta c WHERE c.id =:" + Consulta.ID),
	@NamedQuery(name=Consulta.FIND_BY_DATE, query="SELECT c FROM Consulta c, Instituicao i WHERE i.id = :"+ Instituicao.ID +" AND c.data =:" + Consulta.DATA),
	@NamedQuery(name=Consulta.FIND_ALL_BY_UTENTE, query="SELECT c FROM Consulta c WHERE c.numUtente = :" + Consulta.UTENTE +" ORDER BY c.data DESC"),
	@NamedQuery(name=Consulta.FIND_WITH_DATE, query="SELECT c FROM Consulta c WHERE c.numUtente= :"+ Consulta.UTENTE + " AND c.data > :"+ Consulta.DATA +" ORDER BY c.data DESC")
})
public class Consulta {

	public static final String FIND_ALL_BY_UTENTE = "Consulta.findAllByUtente";
	
	public static final String FIND_BY_ID = "Consulta.FindByID";
	
	public static final String FIND_BY_DATE = "Consulta.FindByDATE";
	
	public static final String FIND_ALL = "Consulta.FindAll";
	
	public static final String FIND_WITH_DATE = "Consulta.FindWithDate";
	
	public static final String UTENTE = "numUtente";
	
	public static final String DATA = "data";
	
	public static final String ID = "id";
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@JsonIgnore
	@NotNull
	private int idMedico;
	
	@JsonIgnore
	@NotNull
	private String numUtente;
	
	@JsonIgnore
	@NotNull
	private int idInstituicao;
	
	@NotNull
	private String sala;
	
	private String observacoes;
	
	

	private boolean feita = false;
	
	public Consulta(){}
	
	public Consulta(int idMedico, String numUtente, int idInstituicao, String sala, Date data, String observacoes){
		this.idMedico = idMedico;
		this.numUtente = numUtente;
		this.idInstituicao = idInstituicao;
		this.data = data;
		this.sala = sala;
		this.observacoes = observacoes;
	}

	public String getObservacoes(){
		return observacoes;
	}
	
	public int getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public String getNumUtente() {
		return numUtente;
	}

	public int getIdInstituicao() {
		return idInstituicao;
	}

	public boolean isFeita() {
		return feita;
	}
	
	public String getSala() {
		return sala;
	}
	
}
