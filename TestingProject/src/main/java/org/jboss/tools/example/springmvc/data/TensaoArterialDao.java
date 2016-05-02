package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.TensaoArterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TensaoArterialDao {

	@Autowired
	private EntityManager em;
	
	public TensaoArterial novaTensaoArterial(int max, int min, int numUtente){
		TensaoArterial tenArt = new TensaoArterial(max, min, numUtente);
		em.persist(tenArt);
		return tenArt;
	}
	
}
