package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.model.Consulta;
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
		//consultaDao.novo(25, 123123123, 123, "1", new Date(), "");
		List<Consulta> all = consultaDao.findAll();
		//----------------------------------
		
		ArrayList<Object> lista= new ArrayList<Object>();
		for(Consulta c : all){
			HashMap<String,Object> mapa = new HashMap<String,Object>();
			mapa.put("id", Integer.toString(c.getId()));
			mapa.put("title", "Consulta");
			mapa.put("start", c.getData());
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
	public ModelAndView marcarConsultaView(@RequestParam(value = "data") Date data, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//TODO meter o lock de sessao
		//-----------------------------------------
		int numUtente = (int) session.getAttribute("sessionID");
		List<Consulta> all = consultaDao.findWithDate(numUtente);
		//-----------------------------------------
		ModelAndView mav = new ModelAndView();
		if(all.size() == 0){
			mav.setViewName("consulta_prompt");
			mav.addObject("data", data);
			return mav;
		}
		else{
			if(all.get(0).getData().equals(data)){
				mav.setViewName("cenas");
				return mav;
			}
			else{
				mav.setViewName("erro");
				return mav;
			}
		}
		
	}
	
	@RequestMapping(value="/persistirConsulta", method = RequestMethod.POST,params={"data", "obs", "instituicao"})
	@ResponseBody
	public boolean persistirConsulta(@RequestParam(value="data") Date data, @RequestParam(value="obs") String obs, 
									 @RequestParam(value="instituicao") String instituicao, HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		String numUtente="123123123";
		consultaDao.novo(13, Integer.parseInt(numUtente), Integer.parseInt(instituicao), "amarela", data, obs);
		return true;
		}
	
	

}







