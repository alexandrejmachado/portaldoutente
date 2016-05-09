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
import org.jboss.tools.example.springmvc.model.Altura;
import org.jboss.tools.example.springmvc.model.Glicemia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GlicemiaDao {

	@Autowired
	private EntityManager em;
	
	public Glicemia novaGlicemia(double valor, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Glicemia glic = new Glicemia(valor, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(glic);
		return glic;
	}
	
	public List<Glicemia> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Glicemia> query = em.createNamedQuery(Altura.FIND_ALL_BY_UTENTE, Glicemia.class);
		query.setParameter(Glicemia.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}
