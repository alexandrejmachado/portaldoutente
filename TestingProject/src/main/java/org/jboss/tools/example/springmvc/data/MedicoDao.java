package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.sensitivedata.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MedicoDao {
	
	@Autowired
	private EntityManager em;
	
	public Medico novoMedico(String nome, String especialidade){
		Medico novoMedico = new Medico(nome, especialidade);
		em.persist(novoMedico);
		return novoMedico;
	}
	
	public Medico findById(int id){
		TypedQuery<Medico> query = em.createNamedQuery(Medico.FIND_BY_ID, Medico.class);
		query.setParameter(Medico.ID, id);
		return query.getSingleResult();
	}

}
