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
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MedicacaoDao {
	
	@Autowired
	private EntityManager em;
	
	public Medicacao novaMedicacao(int numUtente, int idMedicamento, String nomeMedicamento, double dose, String indicacoes, String renovacao, int comprimidosPorCaixa) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Medicacao Medicacao = new Medicacao(Cifras.encrypt(Integer.toString(numUtente)), idMedicamento,nomeMedicamento, dose, indicacoes, renovacao, comprimidosPorCaixa);
		em.persist(Medicacao);
		return Medicacao;
	}
	
	public List<Medicacao> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_ALL_BY_UTENTE, Medicacao.class);
		query.setParameter(Medicacao.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		List<Medicacao> lista = query.getResultList();
		for (Medicacao elem : lista) {
			elem.checkMedicacao();
		}
		return lista;
	}
	
	public Medicacao findById(int id) {
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_BY_ID, Medicacao.class);
		query.setParameter(Medicacao.ID, id);
		return query.getSingleResult();
	}
	
	public boolean deleteMedicacao(int id) {
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_BY_ID, Medicacao.class);
		query.setParameter(Medicacao.ID, id);
		Medicacao med = query.getSingleResult();
		em.remove(med);
		return true;
	}
	
	public boolean exists(int numUtente, int medId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("numUtente " + numUtente);
		System.out.println("medId " + medId);
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_BY_ID_AND_MED, Medicacao.class);
		query.setParameter(Medicacao.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		System.out.println("cifrado " + Cifras.encrypt(Integer.toString(numUtente)));
		query.setParameter(Medicacao.MEDICAMENTO, medId);
		Medicacao medicacao;
		try {medicacao = query.getSingleResult();}
		catch (Exception e) {medicacao = null;}
		if(medicacao == null) {
			return false;
		}
		return true;
	}
	
}
