package org.jboss.tools.example.springmvc.controller;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MesesConverter {
	
	HashMap<Integer, String> meses = new HashMap<Integer, String>();
	
	public MesesConverter(){
		meses.put(0, "Janeiro");
		meses.put(1, "Fevereiro");
		meses.put(2, "Março");
		meses.put(3, "Abril");
		meses.put(4, "Maio");
		meses.put(5, "Junho");
		meses.put(6, "Julho");
		meses.put(7, "Agosto");
		meses.put(8, "Setembro");
		meses.put(9, "Outubro");
		meses.put(10, "Novembro");
		meses.put(11, "Dezembro");
	}
	
	public String getMesById(int mes){
		return this.meses.get(mes);
	}
	
	public HashMap<Integer, String> getMeses(){
		return this.meses;
	}
}
