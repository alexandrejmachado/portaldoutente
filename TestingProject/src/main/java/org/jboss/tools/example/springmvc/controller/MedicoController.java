package org.jboss.tools.example.springmvc.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.MedicoUtenteDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Consulta;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private MedicoUtenteDao muDao;
	
	@Autowired
	private ConsultaDao consultaDao;

	@RequestMapping(value="")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medico_inicio");
		return mav;
	}
	
	@RequestMapping(value="/main", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @RequestParam(value="username") String username) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		session.setAttribute("username", username);
		System.out.println("MEDICO NUMERO: "+username);
		List<Consulta> cu = consultaDao.findWithDateMedico(Integer.parseInt(username));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main_menu_medico");
		mav.addObject("listaParaTratar", cu);
		mav.addObject("username", username);
		mav.addObject("listaUtentes", utenteDao.findByMedico(Integer.parseInt(username)));
		return mav;
	}
	
	
}
