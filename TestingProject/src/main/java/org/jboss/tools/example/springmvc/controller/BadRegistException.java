package org.jboss.tools.example.springmvc.controller;

public class BadRegistException extends Exception{

	private String campo;
	private String message;
	
	public BadRegistException(String message, String campo){
		this.campo = campo;
		this.message= message;
	}
	
	public String getCampo(){
		return campo;
	}
	
	public String getMessage()
	{
		return message;
	}
}
