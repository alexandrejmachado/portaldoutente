package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
	
	public Exame novoExame(int numUtente,Date data,String metalink, String tipo, String nomeUtente, int idMedico) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Exame Exame = new Exame(Cifras.encrypt(Integer.toString(numUtente)),data,metalink, tipo, nomeUtente, idMedico);
		em.persist(Exame);
		return Exame;
	}
	
	public List<Exame> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Exame> query = em.createNamedQuery(Exame.FIND_ALL_BY_UTENTE, Exame.class);
		query.setParameter(Exame.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		return query.getResultList();
	}
	
	public List<Exame> findAllByMedico(int idMedico){
		TypedQuery<Exame> query = em.createNamedQuery(Exame.FIND_ALL_BY_MEDICO, Exame.class);
		query.setParameter(Exame.MEDICO, idMedico);
		return query.getResultList();
	}
	
}
