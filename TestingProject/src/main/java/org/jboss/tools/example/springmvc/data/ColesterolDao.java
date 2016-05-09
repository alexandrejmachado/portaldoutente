package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.tools.example.springmvc.controller.Cifras;
import org.jboss.tools.example.springmvc.model.Colesterol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ColesterolDao {

	@Autowired
	private EntityManager em;
	
	public Colesterol newColesterol(double valor, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Colesterol col = new Colesterol(valor, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(col);
		return col;
	}
	
	public List<Colesterol> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Colesterol> query = em.createNamedQuery(Colesterol.FIND_ALL_BY_UTENTE, Colesterol.class);
		query.setParameter(Colesterol.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}
