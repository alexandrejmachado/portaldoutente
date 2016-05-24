package org.jboss.tools.example.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/testCalendar")
public class EventController {

	@RequestMapping(value="/getEventos")
	@ResponseBody
	public ArrayList<Object> getEventos(){
		ArrayList<Object> lista= new ArrayList<Object>();
		HashMap<String,String> mapa = new HashMap<String,String>();
		mapa.put("id", "0");
		mapa.put("title", "titulo");
		mapa.put("start", "2016-05-01");
		lista.add(mapa);
		return lista;
		
		
	}
	
}
