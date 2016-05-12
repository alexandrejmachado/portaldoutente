package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.controller.Cifras;
import org.jboss.tools.example.springmvc.model.Vacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VacinaDao {
	
	@Autowired
	private EntityManager em;
	
	public Vacina novaVacina(){
		Vacina Vacina = new Vacina();
		em.persist(Vacina);
		return Vacina;
	}
	
	public List<Vacina> findAllByUtente() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Vacina> query = em.createNamedQuery(Vacina.FIND_ALL, Vacina.class);
		return query.getResultList();
	}
	
}
