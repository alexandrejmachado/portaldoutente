package org.jboss.tools.example.springmvc.controller;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MesesConverter {
	
	HashMap<String, String> meses = new HashMap<String, String>();
	
	public MesesConverter(){
		meses.put("Jan","01");
		meses.put("Feb","02");
		meses.put("Mar","03");
		meses.put("Apr","04");
		meses.put("May","05");
		meses.put("Jun","06");
		meses.put("Jul","07");
		meses.put("Aug","08");
		meses.put("Sep","09");
		meses.put("Oct","10");
		meses.put("Nov","11");
		meses.put("Dec","11");
	}
	
	public String getMesByName(String mes){
		return this.meses.get(mes);
	}
	
	public HashMap<String, String> getMeses(){
		return this.meses;
	}
}
