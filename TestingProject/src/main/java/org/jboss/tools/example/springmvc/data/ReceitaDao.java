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
import org.jboss.tools.example.springmvc.model.Receita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReceitaDao {
	
	@Autowired
	private EntityManager em;
	
	public Receita novaReceita(){
		Receita receita = new Receita();
		em.persist(receita);
		return receita;
	}
	
	public List<Receita> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Receita> query = em.createNamedQuery(Receita.FIND_ALL_BY_UTENTE, Receita.class);
		query.setParameter(Receita.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}

}
