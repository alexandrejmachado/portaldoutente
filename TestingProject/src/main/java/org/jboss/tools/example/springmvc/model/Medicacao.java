package org.jboss.tools.example.springmvc.model;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.tools.example.springmvc.controller.Cifras;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Medicacao.FIND_ALL_BY_UTENTE, query="SELECT m FROM Medicacao m WHERE m.numUtente = :" + Medicacao.UTENTE +" ORDER BY m.cal DESC"),
	@NamedQuery(name=Medicacao.DELETE, query="DELETE FROM Medicacao m WHERE m.id = :" + Medicacao.ID),
	@NamedQuery(name=Medicacao.FIND_BY_ID_AND_MED, query="SELECT m FROM Medicacao m WHERE m.numUtente = :" + Medicacao.UTENTE + " AND m.idMedicamento = :"+Medicacao.MEDICAMENTO),
	@NamedQuery(name=Medicacao.FIND_BY_ID, query="SELECT m FROM Medicacao m WHERE m.id = :" + Medicacao.ID),
	@NamedQuery(name=Medicacao.FIND_BY_MEDICO, query="SELECT m FROM Medicacao m WHERE m.medico = :" + Medicacao.MEDICO)
})
public class Medicacao {

	
	@Id
	@JsonIgnore
	@GeneratedValue
	private int id;

	public static final String FIND_BY_ID_AND_MED = "Medicacao.findByIdAndMed";
	
	public static final String FIND_BY_MEDICO = "Medicacao.findByMedico";
	
	public static final String FIND_BY_ID = "Medicacao.findById";
	
	public static final String MEDICAMENTO = "medicamento";
	
	public static final String MEDICO = "medico";
	
	public static final String FIND_ALL_BY_UTENTE = "Medicacao.findAllByUtente";
	
	public static final String DELETE = "Medicacao.delete";
	
	public static final String UTENTE = "numUtente";
	
	public static final String ID = "id";
	
	@JsonIgnore
	private String numUtente;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cal;
	
	@JsonIgnore
	private int idMedicamento;
	
	private String nomeMedicamento;
	

	private boolean estado = false; //esta a true se o medico ja confirmou que esta medicao existe, caso nao tenha confirmado manter-se-a a falso

	private String nomeUtente;
	
	private EstadoRenovacao renovacao;
	
	private Calendar validade;
	
	
	private double dose;
	
	private String indicacoes;
	
	private int comprimidosPorCaixa;
	
	private int medico;
	
	public Medicacao(){}
	
	public Medicacao(String numUtente, int idMedicamento,String nomeMedicamento, double dose, String indicacoes, String renovacao, int comprimidosPorCaixa, int medico, String nomeUtente) {
		this.numUtente = numUtente;
		this.idMedicamento = idMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.dose = dose;
		this.indicacoes = indicacoes;
		setRenovacao(renovacao);
		this.comprimidosPorCaixa = comprimidosPorCaixa;
		this.medico = medico;
		this.nomeUtente = nomeUtente;
		
		validade= Calendar.getInstance();
		validade.add(Calendar.MONTH, 6);
		
		
		cal = Calendar.getInstance();
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getNomeUtente(){
		return nomeUtente;
	}
	
	public int getMedico() {
		return medico;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumUtente() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Cifras.decrypt(numUtente);
	}

	public void setNumUtente(String numUtente) {
		this.numUtente = numUtente;
	}
	
	public String getNomeMedicamento() {
		return nomeMedicamento;
	}
	
	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public Date getDataFinal() {
		return cal.getTime();
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}
	
	public Date getValidade() {
		return validade.getTime();
	}
	
	public void setValidade(int meses) {
		validade.add(Calendar.MONTH, meses);
	}
	
	public String getRenovacao() {
		System.out.println("get = " + renovacao);
		switch (renovacao) {
		case ACEITE:
			return "Aceite";
		case PENDENTE:
			return "Pendente";
		case CADUCADO:
			return "Caducado";
		default:
			return "Rejeitado";
		}
	}
	
	public void setRenovacao(String renovacao) {
		switch (renovacao) {
		case "Aceite":
			this.renovacao = EstadoRenovacao.ACEITE;
			break;
		case "Pendente":
			this.renovacao = EstadoRenovacao.PENDENTE;
			break;
		case "Caducado":
			this.renovacao = EstadoRenovacao.CADUCADO;
			break;
		default:
			this.renovacao = EstadoRenovacao.REJEITADO;
	}
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public double getDose() {
		return dose;
	}

	public void setDose(double dose) {
		this.dose = dose;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getIndicacoes() {
		return indicacoes;
	}

	public void setIndicacoes(String indicacoes) {
		this.indicacoes = indicacoes;
	}

	public int getDiasParaRenovacao() {
		return (int) (comprimidosPorCaixa/dose);
	}
	
	public boolean renovarMedicacao() {
		Calendar cenas = Calendar.getInstance();
		if (cenas.before(validade) && cenas.after(cal) && estado && renovacao==EstadoRenovacao.CADUCADO) {
			renovacao=EstadoRenovacao.ACEITE;
			return true;
		}
		else if (renovacao == EstadoRenovacao.PENDENTE) {
			return false;
		}
		else if (checkMedicacao()) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean checkMedicacao() {
		Calendar cenas = Calendar.getInstance();
		System.out.println(cenas.getTime());
		System.out.println(validade.getTime());
		System.out.println(renovacao);
		if (cenas.after(validade)) {
			System.out.println("1");
			renovacao = EstadoRenovacao.REJEITADO;
			return false;
		}
		else if (renovacao == EstadoRenovacao.CADUCADO) {
			System.out.println(2);
			return false;
		}
		else if (cenas.before(cal)) {
			System.out.println(3);
			setRenovacao("Caducado");
			return false;
		}
		else if (getRenovacao().equals("Pendente")) {
			return true;
		}
		else if (estado){
			System.out.println(4);
			setRenovacao("Aceite");
			return true;
		}
		return false;
	}
}
