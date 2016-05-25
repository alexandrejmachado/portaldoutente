package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Guardiao {

	@Id
	@GeneratedValue
	private int id;
	
	
	private int numeroDependente;
	
	private int numeroGuardiao;
	
	private boolean leitura = false;
	
	private boolean escrita = false;
	
	public Guardiao(){};
	
	public Guardiao(int numeroDependente, int numeroGuardiao) {
		this.numeroDependente = numeroDependente;
		this.numeroGuardiao = numeroGuardiao;
	}

	public int getNumeroDependente() {
		return numeroDependente;
	}

	public void setNumeroDependente(int numeroDependente) {
		this.numeroDependente = numeroDependente;
	}

	public int getNumeroGuardiao() {
		return numeroGuardiao;
	}

	public void setNumeroGuardiao(int numeroGuardiao) {
		this.numeroGuardiao = numeroGuardiao;
	}

	public boolean isLeitura() {
		return leitura;
	}

	public void setLeitura(boolean leitura) {
		this.leitura = leitura;
	}

	public boolean isEscrita() {
		return escrita;
	}

	public void setEscrita(boolean escrita) {
		this.escrita = escrita;
	}
}
