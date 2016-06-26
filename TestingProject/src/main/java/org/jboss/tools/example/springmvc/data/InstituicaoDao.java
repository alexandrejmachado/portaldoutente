package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InstituicaoDao {

	@Autowired
	private EntityManager em;
	
	public Instituicao findById(int id){
		TypedQuery<Instituicao> query = em.createNamedQuery(Instituicao.FIND_BY_ID, Instituicao.class);
		query.setParameter(Instituicao.ID, id);
		return query.getSingleResult();
		}
	
	public List<Instituicao> findByLocalidade(String localidade){
		TypedQuery<Instituicao> query = em.createNamedQuery(Instituicao.FIND_LOCAL, Instituicao.class);
		query.setParameter(Instituicao.LOCALIDADE, localidade);
		return query.getResultList();
		}
	
}
