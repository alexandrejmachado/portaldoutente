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
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CirurgiaDao {
	
	@Autowired
	private EntityManager em;
	
	public Cirurgia novaCirurgia(String numUtente, String idMedico, String tipo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Cirurgia Cirurgia = new Cirurgia(Cifras.encrypt(numUtente), idMedico, tipo);
		em.persist(Cirurgia);
		return Cirurgia;
	}
	
	public List<Cirurgia> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Cirurgia> query = em.createNamedQuery(Cirurgia.FIND_ALL_BY_UTENTE, Cirurgia.class);
		query.setParameter(Cirurgia.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
	
	public Cirurgia findById(int id) {
		TypedQuery<Cirurgia> query = em.createNamedQuery(Cirurgia.FIND_BY_ID, Cirurgia.class);
		query.setParameter(Cirurgia.ID, id);
		return query.getSingleResult();
	}
	
	public boolean confirmarCirurgia (int id) {
		Cirurgia cirurgia = findById(id);
		return cirurgia.confirmar();
		
	}
	
	
}
