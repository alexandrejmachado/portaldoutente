package org.jboss.tools.example.springmvc.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.model.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SessaoDao {

	@Id
	private String id;
	
	
	@Autowired
	private EntityManager em;
	
	public Sessao iniciarSessao(String token, String sessionID, String sessionMode, String sessionName) {
		Sessao sessao = new Sessao(token, sessionID, sessionMode, sessionName);
		em.persist(sessao);
		return sessao;
	}
	
	public Sessao getSessao(String token) {
		try{
			TypedQuery<Sessao> query = em.createNamedQuery(Sessao.FIND_BY_TOKEN, Sessao.class);
			query.setParameter(Sessao.TOKEN, token);
			return query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
	}
	
	public boolean actualiarSessao(Sessao sessao){
		try{
			em.merge(sessao);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean removerSessao(String sessionToken){
		try{
			Sessao session = getSessao(sessionToken);
			em.remove(session);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
}
