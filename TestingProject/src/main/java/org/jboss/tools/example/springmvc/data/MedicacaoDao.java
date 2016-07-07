package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.controller.Cifras;
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@Transactional
public class MedicacaoDao {
	
	@Autowired
	private EntityManager em;
	
	public Medicacao novaMedicacao(int numUtente, int idMedicamento, String nomeMedicamento, double dose, String indicacoes, String renovacao, int comprimidosPorCaixa, int medico, String nomeUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Medicacao Medicacao = new Medicacao(Cifras.encrypt(Integer.toString(numUtente)), idMedicamento,nomeMedicamento, dose, indicacoes, renovacao, comprimidosPorCaixa, medico, nomeUtente);
		em.persist(Medicacao);
		return Medicacao;
	}
	
	public List<Medicacao> findAllByUtente(int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_ALL_BY_UTENTE, Medicacao.class);
		query.setParameter(Medicacao.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		List<Medicacao> lista = query.getResultList();
		for (Medicacao elem : lista) {
			elem.checkMedicacao();
			em.persist(elem);
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
	
	public String renovarMed(int id) {
		Medicacao medAtual = findById(id);
		String estado = medAtual.renovarMedicacao();
		em.persist(medAtual);
		System.out.println(medAtual.getRenovacao());
		return estado;
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

	public List<Medicacao> findByMedico(int idMedico) {
		TypedQuery<Medicacao> query = em.createNamedQuery(Medicacao.FIND_BY_MEDICO, Medicacao.class);
		query.setParameter(Medicacao.MEDICO, idMedico);
		return query.getResultList();
	}
	
	public List<Medicacao> findByMedicoPendente(int idMedico) {
		Query query = em.createNativeQuery("SELECT * FROM Medicacao WHERE Medicacao.renovacao = 0 AND Medicacao.medico = ?", Medicacao.class);
		query.setParameter(1, idMedico);
		return query.getResultList();
	}
	
	public boolean aceitar(int id){
		Medicacao med = findById(id);
		med.setRenovacao("Aceite");
		med.setValidade(6);
		em.merge(med);
		return true;
	}
	
	public boolean rejeitar(int id){
		deleteMedicacao(id);
		return true;
	}
	
}
