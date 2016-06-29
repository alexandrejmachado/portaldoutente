package org.jboss.tools.example.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {
	
	@Autowired
	private UtenteDao utenteDao;

	@RequestMapping(value="")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medico_inicio");
		return mav;
	}
	
	@RequestMapping(value="/main", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @RequestParam(value="username") String username){
		session.setAttribute("username", username);
		System.out.println("MEDICO NUMERO: "+username);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main_menu_medico");
		mav.addObject("username", username);
		mav.addObject("listaUtentes", utenteDao.findByMedico(Integer.parseInt(username)));
		return mav;
	}
	
	@RequestMapping(value="/consultasPendentes", method = RequestMethod.POST)
	public ModelAndView consultasPendentes(@RequestParam(value="numUtente") String numUtente, HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main_menu_medico");
		mav.addObject("utente", numUtente);
		mav.addObject("username", session.getAttribute("username"));
		return mav;
	}
}
