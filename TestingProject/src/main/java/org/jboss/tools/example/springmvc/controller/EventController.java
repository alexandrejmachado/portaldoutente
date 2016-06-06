package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jboss.tools.example.springmvc.controller.Cifras;

@Controller
@RequestMapping(value="/testCalendar")
public class EventController {
	
	@Autowired
	private ConsultaDao consultaDao;

	@RequestMapping(value="/getEventos")
	@ResponseBody
	public ArrayList<Object> getEventos() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//----------------------------------
		//consultaDao.novo(1, 123123123, 123, "1", new Date());
		//----------------------------------
		ArrayList<Object> lista= new ArrayList<Object>();
		for(int i = 0; i<2; i++){
			HashMap<String,Object> mapa = new HashMap<String,Object>();
			mapa.put("id", Integer.toString(i));
			mapa.put("title", "titulo");
			mapa.put("start", new Date().getTime());
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
