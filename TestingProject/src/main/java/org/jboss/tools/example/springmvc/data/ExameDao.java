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
import org.jboss.tools.example.springmvc.model.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ExameDao {

	@Autowired
	private EntityManager em;
	
	public Exame novoExame(){
		Exame Exame = new Exame();
		em.persist(Exame);
		return Exame;
	}
	
	public List<Exame> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Exame> query = em.createNamedQuery(Exame.FIND_ALL_BY_UTENTE, Exame.class);
		query.setParameter(Exame.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
	
	
}
