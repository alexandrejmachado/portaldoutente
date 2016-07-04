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
	
	private String sessionID;
	
	private String sessionMode;
	
	private String sessionName;
	
	private String token;
	
	public Sessao() {}
	
	public Sessao(String token, String sessionID, String sessionMode, String sessionName) {
		this.token = token;
		this.sessionID = sessionID;
		this.sessionMode = sessionMode;
		this.sessionName = sessionName;
	}
	
	
	public int getId(){
		return id;
	}
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getSessionMode() {
		return sessionMode;
	}

	public void setSessionMode(String sessionMode) {
		this.sessionMode = sessionMode;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getToken() {return token;}
	
	public void setToken(String token) {this.token = token;}
	
	
}
