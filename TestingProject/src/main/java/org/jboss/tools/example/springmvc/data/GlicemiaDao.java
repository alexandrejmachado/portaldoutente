package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.Glicemia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GlicemiaDao {

	@Autowired
	private EntityManager em;
	
	public Glicemia novaGlicemia(double valor, int numUtente){
		Glicemia glic = new Glicemia(valor, numUtente);
		em.persist(glic);
		return glic;
	}
}
