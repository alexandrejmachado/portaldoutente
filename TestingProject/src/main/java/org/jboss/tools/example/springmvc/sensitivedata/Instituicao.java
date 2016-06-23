package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.jboss.tools.example.springmvc.model.Consulta;

@Entity
@NamedQueries({
	@NamedQuery(name=Instituicao.FIND_LOCAL, query="SELECT i FROM Instituicao i WHERE i.localidade = :"+ Instituicao.LOCALIDADE)})
public class Instituicao {
	
	public static final String FIND_LOCAL = "Instituicao.findLocal";
	
	public static final String LOCALIDADE = "localidade";
	
	public static final String ID = "id";
	
	@Id
	private int id;
	
	private String morada;
	
	private String telefone;
	
	private String email;
	
	private String nome;
	
	private String tipo;
	
	private String localidade;
	
	

	public Instituicao(){}
	
	public Instituicao(String morada,String telefone, String email, String nome, String localidade, String tipo){
		this.morada = morada;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.tipo = tipo;
		this.localidade = localidade;
	}

	public int getId() {
		return id;
	}

	public String getMorada() {
		return morada;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
}
