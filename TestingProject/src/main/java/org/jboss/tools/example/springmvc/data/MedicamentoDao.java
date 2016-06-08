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
import org.jboss.tools.example.springmvc.model.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MedicamentoDao {
	
	@Autowired
	private EntityManager em;
	
	public Medicamento novoMedicamento() {
		Medicamento Medicamento = new Medicamento();
		em.persist(Medicamento);
		return Medicamento;
	}
	
	
	public Medicamento findByNome(String nome) {
		TypedQuery<Medicamento> query = em.createNamedQuery(Medicamento.FIND_BY_NAME, Medicamento.class);
		query.setParameter(Medicamento.NOME, nome);
		return query.getSingleResult();
	}
	
	
}
