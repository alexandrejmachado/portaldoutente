package org.jboss.tools.example.springmvc.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/testCalendar")
public class EventController {

	@RequestMapping(value="/getEventos")
	@ResponseBody
	public ArrayList<Object> getEventos(){
		ArrayList<Object> lista= new ArrayList<Object>();
		for(int i = 0; i<2; i++){
			HashMap<String,String> mapa = new HashMap<String,String>();
			mapa.put("id", Integer.toString(i));
			mapa.put("title", "titulo");
			mapa.put("start", "2016-05-01T16:00:00-18:00");
			//mapa.put("draggable", "false");
			lista.add(mapa);
		}
		return lista;
		
	}
	
	@RequestMapping(value="/view")
	public ModelAndView calendarView(){
		//TODO meter o lock de sessao
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calendarView");
		return mav;
	}
	
	@RequestMapping(value="/marcarConsultaView", method = RequestMethod.GET, params={"data"})
	public ModelAndView marcarConsultaView(@RequestParam(value = "data") String date){
		//TODO meter o lock de sessao
		ModelAndView mav = new ModelAndView();
		mav.setViewName("consulta_prompt");
		mav.addObject("data", date);
		return mav;
	}
	
}
