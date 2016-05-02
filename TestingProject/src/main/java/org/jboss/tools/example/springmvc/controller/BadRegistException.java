package org.jboss.tools.example.springmvc.controller;

public class BadRegistException extends Exception{

	private String campo;
	
	public BadRegistException(String message, String campo){
		this.campo = campo;
	}
	
	public String getCampo(){
		return campo;
	}
}
