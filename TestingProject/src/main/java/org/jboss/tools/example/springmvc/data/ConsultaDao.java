package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;

import org.jboss.tools.example.springmvc.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConsultaDao {

	@Autowired
	private EntityManager em;
	
	public Consulta novaConsulta(){
		return null;
	}
}
