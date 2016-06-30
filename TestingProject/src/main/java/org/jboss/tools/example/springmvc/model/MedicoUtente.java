package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries({
	@NamedQuery(name=MedicoUtente.FIND_BY_MED_AND_UTENTE, query="SELECT m FROM MedicoUtente m WHERE m.medico = :" + MedicoUtente.MEDICO + " AND m.utente = :" + MedicoUtente.UTENTE),
	@NamedQuery(name=MedicoUtente.FIND_BY_MED, query="SELECT m FROM MedicoUtente m WHERE m.medico = :" + MedicoUtente.MEDICO),
	@NamedQuery(name=MedicoUtente.FIND_BY_UTENTE, query="SELECT m FROM MedicoUtente m WHERE m.utente = :" + MedicoUtente.UTENTE)
})
public class MedicoUtente {


	public static final String FIND_BY_MED_AND_UTENTE = "MedicoUtente.findByMedAndUtente";
	
	public static final String FIND_BY_MED = "MedicoUtente.findByMed";
	
	public static final String FIND_BY_UTENTE = "MedicoUtente.findByUtente";
	
	public static final String MEDICO = "medico";
	
	public static final String UTENTE = "utente";
	
	@Id
	@GeneratedValue
	private int id;
	
	private int medico;
	
	private int utente;
	
	private boolean altura = false;
	
	private boolean colesterol = false;
	
	private boolean glicemia = false;
	
	private boolean inr = false;
	
	private boolean peso = false;
	
	private boolean saturacao = false;
	
	private boolean tensao = false;
	
	private boolean trigliceridos = false;
	
	public MedicoUtente(){}
	
	public MedicoUtente( int medico, int utente){
		this.medico = medico;
		this.utente = utente;
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public int getMedico() {
		return medico;
	}

	@JsonIgnore
	public void setMedico(int medico) {
		this.medico = medico;
	}

	@JsonIgnore
	public int getUtente() {
		return utente;
	}

	@JsonIgnore
	public void setUtente(int utente) {
		this.utente = utente;
	}

	public boolean isAltura() {
		return altura;
	}

	public void setAltura(boolean altura) {
		this.altura = altura;
	}

	public boolean isColesterol() {
		System.out.println("get = " + colesterol);
		return colesterol;
		
	}

	public void setColesterol(boolean colesterol) {
		System.out.println(this.colesterol);
		this.colesterol = colesterol;
		System.out.println(this.colesterol);
	}

	public boolean isGlicemia() {
		return glicemia;
	}

	public void setGlicemia(boolean glicemia) {
		this.glicemia = glicemia;
	}

	public boolean isInr() {
		return inr;
	}

	public void setInr(boolean inr) {
		this.inr = inr;
	}

	public boolean isPeso() {
		return peso;
	}

	public void setPeso(boolean peso) {
		this.peso = peso;
	}

	public boolean isSaturacao() {
		return saturacao;
	}

	public void setSaturacao(boolean saturacao) {
		this.saturacao = saturacao;
	}

	public boolean isTensao() {
		return tensao;
	}

	public void setTensao(boolean tensao) {
		this.tensao = tensao;
	}

	public boolean isTrigliceridos() {
		return trigliceridos;
	}

	public void setTrigliceridos(boolean trigliceridos) {
		this.trigliceridos = trigliceridos;
	}
	
	
	
	
}
