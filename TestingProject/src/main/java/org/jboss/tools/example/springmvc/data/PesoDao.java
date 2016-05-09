package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.Peso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PesoDao {

	@Autowired
	private EntityManager em;
	
	public Peso novoPeso(double valor, int numUtente){
		Peso peso = new Peso(valor, numUtente);
		em.persist(peso);
		return peso;
	}
}