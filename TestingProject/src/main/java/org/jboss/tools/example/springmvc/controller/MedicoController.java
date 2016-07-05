package org.jboss.tools.example.springmvc.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.MedicoUtenteDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Consulta;
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private MedicoDao medicoDao;
	
	@Autowired
	private MedicoUtenteDao muDao;
	
	@Autowired
	private ConsultaDao consultaDao;
	
	@Autowired
	private MedicacaoDao medicacaoDao;

	@RequestMapping(value="")
	public ModelAndView index(){
		//TODO por css na view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medico_inicio");
		return mav;
	}
	
	@RequestMapping(value="/entrar", method = RequestMethod.POST)
	public ModelAndView entrar(HttpSession session, @RequestParam(value="username") String username, @RequestParam(value="pass") String pass){
		if(medicoDao.findById(Integer.parseInt(username)) != null && username.equals(pass)){
			session.setAttribute("username", username);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/medico/main");
			return mav;
		}
		else{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("medico_inicio");
			return mav;
		}
	}
	
	@RequestMapping(value="/main")
	public ModelAndView login(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			String username = (String) session.getAttribute("username");
			System.out.println("MEDICO NUMERO: "+username);
			int idMedico = Integer.parseInt(username);
			//Apanhar as Consultas
			List<Consulta> cu = consultaDao.findWithDateMedico(idMedico);
			//--------------------
			//Apanhar os Utentes do medico
			List<Utente> ut = utenteDao.findByMedico(idMedico);
			//----------------------------
			//Apanhar as Medicacoes para renovar
			List<Medicacao> medicacaoRows = medicacaoDao.findByMedicoPendente(idMedico);
			//----------------------------------
			mav.setViewName("main_menu_medico");
			mav.addObject("listaParaTratar", cu);
			mav.addObject("listaUtentes",ut);
			mav.addObject("username", username);
			mav.addObject("medicacao", medicacaoRows);
			mav.addObject("listaUtentes", utenteDao.findByMedico(Integer.parseInt(username)));
			return mav;
		}
		else{
			mav.setViewName("redirect:/medico");
			return mav;
		}
	}
	
	@RequestMapping(value="/confirmarConsulta", method = RequestMethod.POST)
	@ResponseBody
	public boolean confirmarConsulta(HttpSession session,@RequestParam(value="idConsulta") String idConsulta){
		try{
			Consulta cons = consultaDao.findById(Integer.parseInt(idConsulta));
			consultaDao.confirmarConsulta(cons);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@RequestMapping(value="/removerConsulta", method = RequestMethod.POST)
	@ResponseBody
	public boolean rejeitarConsulta(HttpSession session,@RequestParam(value="idConsulta") String idConsulta){
		try{
			consultaDao.remove(Integer.parseInt(idConsulta));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@RequestMapping(value="/consultarDados", method = RequestMethod.GET)
	public ModelAndView consultarDados(HttpSession session,@ModelAttribute(value="utente") String utente){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			String username = (String) session.getAttribute("username");
			System.out.println("MEDICO NUMERO: "+username);
			//----Apanhar Medidasd Partilhadas---------
			MedicoUtente mu = muDao.findByUtente(utente);
			List<String> shared = new ArrayList<String>();
			if(mu.isAltura()){
				shared.add("Altura");
			}
			if(mu.isColesterol()){
				shared.add("Colesterol");
			}
			if(mu.isGlicemia()){
				shared.add("Glicemia");
			}
			if(mu.isInr()){
				shared.add("INR");
			}
			if(mu.isPeso()){
				shared.add("Peso");
			}
			if(mu.isSaturacao()){
				shared.add("SaturaçãoO2");
			}
			if(mu.isTensao()){
				shared.add("Tensão Arterial");
			}
			if(mu.isTrigliceridos()){
				shared.add("Triglicéridos");
			}
			if(shared.size() == 0){
				shared.add("Sem Medidas Partilhadas");
				System.out.println("vazio");
			}
			//-----------------------------------------
			mav.addObject("username",username);
			mav.addObject("medidas", shared);
			mav.setViewName("medico_medicoes");
		}
		else{
			mav.setViewName("redirect:/medico");
		}
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("username");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medico_inicio");
		return mav;
	}
	
	public boolean verifyLogin(HttpSession session){
		if(session.getAttribute("username") == null){
			return false;
		}
		else{
			return true;
		}
			
	}
	
	@RequestMapping(value="/aceitarMedicacao", method = RequestMethod.POST)
	@ResponseBody
	public boolean aceitarMedicacao(@RequestParam(value="idMedicacao") String idMedicacao){
		medicacaoDao.aceitar(Integer.parseInt(idMedicacao));
		return true;
		
	}
	
	@RequestMapping(value="/visualizar/{tipoMedida}/{utente}", method = RequestMethod.GET)
	public ModelAndView showMedidas(HttpServletRequest request,@PathVariable("tipoMedida") String tipoMedida,@PathVariable("utente") String utente)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("medida", tipoMedida);
		mav.addObject("utente", utente);
		if(tipoMedida.equals("TensaoArterial"))
		{
			mav.setViewName("graficos2");
		}
		else{
			mav.setViewName("graficos");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/rejeitarMedicacao", method = RequestMethod.POST)
	@ResponseBody
	public boolean rejeitarMedicacao(@RequestParam(value="idMedicacao") String idMedicacao){
		medicacaoDao.rejeitar(Integer.parseInt(idMedicacao));
		return true;
		
	}
}
