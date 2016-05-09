package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import org.jboss.tools.example.springmvc.model.Altura;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
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
	
	public List<Altura> findAllByUtente(int numUtente){
		//TODO ver a cena de crifrar
		TypedQuery<Altura> query = em.createNamedQuery(Altura.FIND_ALL_BY_UTENTE, Altura.class);
		query.setParameter(Altura.UTENTE, numUtente);
		return null;
	}
	
}
