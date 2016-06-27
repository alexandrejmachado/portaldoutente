package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
		Instituicao temp=null;
		TypedQuery<Instituicao> query = em.createNamedQuery(Instituicao.FIND_LOCAL, Instituicao.class);
		if(localidade.equals("Balelas")){temp=new Instituicao("1","2","3","2","Balelas","asd");}
		query.setParameter(Instituicao.LOCALIDADE, localidade);
		if(temp.equals(null)){return query.getResultList();}
		else{return new ArrayList<Instituicao>(Arrays.asList(new Instituicao[]{temp}));}
		
		}
	
}
