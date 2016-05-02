package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.INR;
import org.jboss.tools.example.springmvc.model.TensaoArterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class INRDao {

	@Autowired
	private EntityManager em;
	
	public INR novoINR(double valor, int numUtente){
		INR inr = new INR(valor, numUtente);
		em.persist(inr);
		return inr;
	}
	
}
