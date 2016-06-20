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
import org.jboss.tools.example.springmvc.model.Medicamentoid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MedicamentoIdDao {
	
	@Autowired
	private EntityManager em;
	
	public Medicamentoid novoMedicamento() {
		Medicamentoid medicamentoId = new Medicamentoid();
		em.persist(medicamentoId);
		return medicamentoId;
	}
	
	
	public Medicamentoid findByNome(String nome) {
		TypedQuery<Medicamentoid> query = em.createNamedQuery(Medicamentoid.FIND_BY_NAME, Medicamentoid.class);
		query.setParameter(Medicamentoid.NOME, nome);
		return query.getSingleResult();
	}
	
	
}