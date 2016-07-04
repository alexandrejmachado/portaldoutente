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
	
	public void iniciarSessao(String token) {
		Sessao sessao = new Sessao(token);
		em.persist(sessao);
	}
	
	private Sessao getSessao(String token) {
		TypedQuery<Sessao> query = em.createNamedQuery(Sessao.FIND_BY_TOKEN, Sessao.class);
		query.setParameter(Sessao.TOKEN, token);
		return query.getSingleResult();
	}
	
	public void setAttribute(String token, String nome, Object obj) {
		Sessao sessao = getSessao(token);
		sessao.setAttribute(nome, obj);
		em.persist(sessao);
	}
	
	public Object getAttribute(String token, String nome) {
		Sessao sessao = getSessao(token);
		em.persist(sessao);
		return sessao.getAttribute(nome);
	}
	
}
