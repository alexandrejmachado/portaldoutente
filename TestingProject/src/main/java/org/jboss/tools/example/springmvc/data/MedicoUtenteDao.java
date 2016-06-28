package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.reflect.Typed;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.jboss.tools.example.springmvc.sensitivedata.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MedicoUtenteDao {

	@Autowired
	private EntityManager em;
	
	public MedicoUtente novo(int medico, String numUtente){
		System.out.println("something is wrong");
		MedicoUtente mu = new MedicoUtente(medico, numUtente);
		em.persist(mu);
		return mu;
	}
	
	public MedicoUtente findByMedicoAndUtente(int utente, int medico){
		TypedQuery<MedicoUtente> query = em.createNamedQuery(MedicoUtente.FIND_BY_MED_AND_UTENTE, MedicoUtente.class);
		query.setParameter(MedicoUtente.MEDICO, medico);
		query.setParameter(MedicoUtente.UTENTE, utente);
		return query.getSingleResult();
	}
	
	public List<MedicoUtente> findByMedico(int medico){
		TypedQuery<MedicoUtente> query = em.createNamedQuery(MedicoUtente.FIND_BY_MED, MedicoUtente.class);
		query.setParameter(MedicoUtente.MEDICO, medico);
		return query.getResultList();
	}
}
