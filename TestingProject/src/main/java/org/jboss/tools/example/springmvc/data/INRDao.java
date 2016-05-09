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
	
	public INR novoINR(double valor, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		INR inr = new INR(valor, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(inr);
		return inr;
	}
	
	public List<INR> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<INR> query = em.createNamedQuery(INR.FIND_ALL_BY_UTENTE, INR.class);
		query.setParameter(INR.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}
