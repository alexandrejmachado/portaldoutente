package org.jboss.tools.example.springmvc.sensitivedata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instituicao {
	
	@Id
	private int id;
	
	public Instituicao(){}
	
}
