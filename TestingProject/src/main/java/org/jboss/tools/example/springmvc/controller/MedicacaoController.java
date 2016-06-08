package org.jboss.tools.example.springmvc.controller;

import java.util.Date;

import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/medicacao")
public class MedicacaoController {

	@Autowired
	private UtenteDao utenteDao;
	
	private AuthController as= new AuthController();
	
	
	@RequestMapping(value="/view")
	public ModelAndView calendarView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medicamentos");
		return mav;
	}



}
