package org.jboss.tools.example.springmvc.controller;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Medicamento;

import java.util.Date;

import org.jboss.tools.example.springmvc.data.UtenteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/medicacao")
public class MedicacaoController {

	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	public MedicamentoDao medDao;
	
	@Autowired
	public UtenteDao utDao;
	
	@Autowired
	public MedicacaoDao medicacaoDao;
	
	
	
	@RequestMapping(value="/inserir", method = RequestMethod.POST,params={"nome", "dosagem", "indicacoes"})
	public void inserirMedicacao(HttpSession session, @RequestParam(value="nome") String nomeMedicamento, @RequestParam(value="dosagem") double dosagemDiaria, @RequestParam(value="indicacoes") String indicacoes) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Medicamento med = medDao.findByNome(nomeMedicamento);
		medicacaoDao.novaMedicacao(Integer.parseInt((String) session.getAttribute("sessionID")), med.getId(), dosagemDiaria, indicacoes, "Pendente", med.getComprimidos());
	}
	
	@RequestMapping(value="/verificar")
	public void verificarMedicacao(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		medicacaoDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID"))); 
	}

	private AuthController as= new AuthController();
	
	
	@RequestMapping(value="/view")
	public ModelAndView calendarView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medicamentos");
		return mav;
	}


}
