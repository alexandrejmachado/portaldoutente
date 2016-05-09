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
import org.jboss.tools.example.springmvc.model.SaturacaoO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SaturacaoO2Dao {
	
	@Autowired
	private EntityManager em;
	
	public SaturacaoO2 novaAltura(double valor, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		SaturacaoO2 altura = new SaturacaoO2(valor, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(altura);
		return altura;
	}
	
	public List<SaturacaoO2> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<SaturacaoO2> query = em.createNamedQuery(SaturacaoO2.FIND_ALL_BY_UTENTE, SaturacaoO2.class);
		query.setParameter(SaturacaoO2.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}
