package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

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
	
	public Instituicao findById(int id){return null;}
	
	public List<Instituicao> findByLocalidade(String localidade){return null;}
	
}
