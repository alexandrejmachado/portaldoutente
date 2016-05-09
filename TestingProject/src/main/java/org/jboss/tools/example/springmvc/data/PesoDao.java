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
import org.jboss.tools.example.springmvc.model.Altura;
import org.jboss.tools.example.springmvc.model.Peso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PesoDao {

	@Autowired
	private EntityManager em;
	
	public Peso novoPeso(double valor, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Peso peso = new Peso(valor, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(peso);
		return peso;
	}
	
	public List<Peso> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Peso> query = em.createNamedQuery(Peso.FIND_ALL_BY_UTENTE, Peso.class);
		query.setParameter(Peso.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}