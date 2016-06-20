package org.jboss.tools.example.springmvc.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoIdDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Medicamento;
import org.jboss.tools.example.springmvc.model.Medicamentoid;

import java.util.Date;
import java.util.HashMap;

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
	
	@Autowired
	public MedicamentoIdDao medidDao;
	
	public HashMap<String,String> map = new HashMap<String, String>();

	
	@RequestMapping(value="/inserir", method = RequestMethod.POST,params={"nome", "dosagem", "indicacoes"})
	public boolean inserirMedicacao(HttpSession session, @RequestParam(value="nome") String nomeMedicamento, @RequestParam(value="dosagem") double dosagemDiaria, @RequestParam(value="indicacoes") String indicacoes) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("cheguei aqui");
		
		Medicamentoid medid= medidDao.findByNome(nomeMedicamento);
		int id = medid.getID();
		Medicamento med = medDao.findById(Integer.toString(id));
		medicacaoDao.novaMedicacao(Integer.parseInt((String) session.getAttribute("sessionID")), med.getId(), dosagemDiaria, indicacoes, "Pendente", med.getComprimidos());
		return true;
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
