package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.Altura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AlturaDao {
	
	@Autowired
	private EntityManager em;
	
	public Altura novaAltura(double valor, int numUtente){
		Altura altura = new Altura(valor, numUtente);
		em.persist(altura);
		return altura;
	}
	
}
