package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import org.jboss.tools.example.springmvc.controller.Cifras;
import org.jboss.tools.example.springmvc.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConsultaDao {

	@Autowired
	private EntityManager em;
	
	public Consulta novo(int idMedico, int numUtente, int idInstituicao, String sala, Date data, String observacoes) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Consulta Consulta = new Consulta(idMedico, Cifras.encrypt(Integer.toString(numUtente)), idInstituicao, sala, data, observacoes);
		em.persist(Consulta);
		return Consulta;
	}
	
	public boolean confirmarConsulta(Consulta cons){
		cons.setConfirmada();
		em.merge(cons);
		return true;
	}
	
	
	public boolean remove(int consultaId){
		try{
			Consulta consulta = findById(consultaId);
			em.remove(consulta);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Consulta findById(int id){
		TypedQuery<Consulta> query = em.createNamedQuery(Consulta.FIND_BY_ID, Consulta.class);
		query.setParameter(Consulta.ID, id);
		return query.getSingleResult();
	}
	
	public List<Consulta> findByDate(String data, int instituicao) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		// usado para ver as horas disponiveis do dia
		try{
			Query query = em.createNativeQuery("SELECT * FROM Consulta WHERE Consulta.idInstituicao = ? AND Consulta.data LIKE ?",Consulta.class);
			String actualDate = new java.sql.Date(Long.parseLong(data)).toString() + "%";
			//query.setParameter(1, 1);
			query.setParameter(1, instituicao);
			query.setParameter(2, actualDate);
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Consulta findByUtenteAndData(int numUtente, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Query query = em.createNativeQuery("SELECT * FROM Consulta WHERE Consulta.numUtente = ? AND Consulta.data LIKE ?",Consulta.class);
		String actualDate = new java.sql.Date(Long.parseLong(data)).toString() + "%";
		query.setParameter(1,Cifras.encrypt(Integer.toString(numUtente)));
		query.setParameter(2,actualDate);
		return (Consulta) query.getSingleResult();
	}
	
	public List<Consulta> findAllByUtente(int numUtente, int instituicao) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Consulta> query = em.createNamedQuery(Consulta.FIND_ALL_BY_UTENTE, Consulta.class);
		query.setParameter(Consulta.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		query.setParameter(Consulta.INST_ID, instituicao);
		return query.getResultList();
	}
	
	public List<Consulta> findAll() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		TypedQuery<Consulta> query = em.createNamedQuery(Consulta.FIND_ALL, Consulta.class);
		return query.getResultList();
	}
	
	public List<Consulta> findWithDate(int numUtente, int instituicao) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		TypedQuery<Consulta> query = em.createNamedQuery(Consulta.FIND_WITH_DATE, Consulta.class);
		query.setParameter(Consulta.UTENTE, Cifras.encrypt(Integer.toString(numUtente)));
		query.setParameter(Consulta.DATA, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		query.setParameter(Consulta.INST_ID, instituicao );
		return query.getResultList();
	}
	
public List<Consulta> findWithDateMedico(int idMedico) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		TypedQuery<Consulta> query = em.createNamedQuery(Consulta.FIND_WITH_DATE_MEDICO, Consulta.class);
		query.setParameter(Consulta.ID_MEDICO, idMedico);
		query.setParameter(Consulta.DATA, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		return query.getResultList();
	}


}
