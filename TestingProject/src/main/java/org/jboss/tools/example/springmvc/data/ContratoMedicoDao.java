package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.sensitivedata.ContratoMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ContratoMedicoDao {

	@Autowired
	private EntityManager em;
	
	public ContratoMedico novoContratoMedico(int nome, int especialidade){
		ContratoMedico novoContratoMedico = new ContratoMedico(nome, especialidade);
		em.persist(novoContratoMedico);
		return novoContratoMedico;
	}
	
	public List<ContratoMedico> findByCentro(int centroId){
		TypedQuery<ContratoMedico> query = em.createNamedQuery(ContratoMedico.FIND_BY_CENTRO, ContratoMedico.class);
		query.setParameter(ContratoMedico.CENTRO_ID,centroId);
		return query.getResultList();
	}
}
