package org.jboss.tools.example.springmvc.model;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name=Sessao.FIND_BY_TOKEN, query="SELECT s FROM Sessao s WHERE s.token = :" + Sessao.TOKEN)
})
public class Sessao {

	public static final String FIND_BY_TOKEN = "Sessao.findByToken";
	
	public static final String TOKEN = "token";
	
	@Id
	@GeneratedValue
	private int id;
	
	private Map<String,Object> mapa = new HashMap<String,Object>();
	
	private String token;
	
	public Sessao() {}
	
	public Sessao(String token) {
		this.token = token;
	}
	
	public String getToken() {return token;}
	
	public void setToken(String token) {this.token = token;}
	
	public void setAttribute(String nome, Object obj) {
		mapa.put(nome, obj);
	}
	
	public Object getAttribute(String nome) {
		return mapa.get(nome);
	}
	
}
