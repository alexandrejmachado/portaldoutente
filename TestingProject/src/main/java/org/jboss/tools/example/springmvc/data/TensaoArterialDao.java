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
import org.jboss.tools.example.springmvc.model.TensaoArterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TensaoArterialDao {

	@Autowired
	private EntityManager em;
	
	public TensaoArterial novaTensaoArterial(int max, int min, int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TensaoArterial tenArt = new TensaoArterial(max, min, Cifras.encrypt(Integer.toString(numUtente)));
		em.persist(tenArt);
		return tenArt;
	}

	public List<TensaoArterial> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<TensaoArterial> query = em.createNamedQuery(TensaoArterial.FIND_ALL_BY_UTENTE, TensaoArterial.class);
		query.setParameter(TensaoArterial.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
}
