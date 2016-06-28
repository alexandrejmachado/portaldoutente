package org.jboss.tools.example.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name=MedicoUtente.FIND_BY_MED_AND_UTENTE, query="SELECT m FROM MedicoUtente m WHERE m.medico = :" + MedicoUtente.MEDICO + " AND m.utente = :" + MedicoUtente.UTENTE),
	@NamedQuery(name=MedicoUtente.FIND_BY_MED, query="SELECT m FROM MedicoUtente m WHERE m.medico = :" + MedicoUtente.MEDICO)
})
public class MedicoUtente {


	public static final String FIND_BY_MED_AND_UTENTE = "MedicoUtente.findByMedAndUtente";
	
	public static final String FIND_BY_MED = "MedicoUtente.findByMed";
	
	public static final String MEDICO = "medico";
	
	public static final String UTENTE = "utente";
	
	@Id
	@GeneratedValue
	private int id;
	
	private int medico;
	
	private String utente;
	
	private boolean altura = false;
	
	private boolean colesterol = false;
	
	private boolean glicemia = false;
	
	private boolean inr = false;
	
	private boolean peso = false;
	
	private boolean saturacao = false;
	
	private boolean tensao = false;
	
	private boolean trigliceridos = false;
	
	public MedicoUtente(){}
	
	public MedicoUtente( int medico, String utente){
		this.medico = medico;
		this.utente = utente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMedico() {
		return medico;
	}

	public void setMedico(int medico) {
		this.medico = medico;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public boolean isAltura() {
		return altura;
	}

	public void setAltura(boolean altura) {
		this.altura = altura;
	}

	public boolean isColesterol() {
		return colesterol;
	}

	public void setColesterol(boolean colesterol) {
		this.colesterol = colesterol;
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
