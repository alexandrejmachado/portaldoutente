package org.jboss.tools.example.springmvc.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.ExameDao;
import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.MedicoUtenteDao;
import org.jboss.tools.example.springmvc.data.SessaoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Consulta;
import org.jboss.tools.example.springmvc.model.Exame;
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.jboss.tools.example.springmvc.model.Sessao;
import org.jboss.tools.example.springmvc.sensitivedata.Medico;
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
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private ExameDao exameDao;

	@RequestMapping(value="")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("medico_inicio");
		return mav;
	}
	
	public String getToken() {
		SecureRandom random = new SecureRandom();
		String s = new BigInteger(130, random).toString(32);
		return s;
	}
	
	@RequestMapping(value="/entrar", method = RequestMethod.POST)
	public ModelAndView entrar(HttpServletResponse response, @RequestParam(value="username") String username, @RequestParam(value="pass") String pass){
		if(medicoDao.findById(Integer.parseInt(username)) != null && username.equals(pass)){
			String token = getToken();
			Cookie cookie = new Cookie("sessionToken", token);
			cookie.setMaxAge(1800);
			response.addCookie(cookie);
			Medico m = medicoDao.findById(Integer.parseInt(username));
			sessaoDao.iniciarSessao(token, username, "medico", m.getNome());
			
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
	public ModelAndView login(HttpServletRequest request) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		ModelAndView mav = new ModelAndView();
		String token = getSessaoToken(request);
		if(verifyLogin(token)){
			Sessao session = sessaoDao.getSessao(token);
			String username = session.getSessionName();
			System.out.println("MEDICO: "+username);
			int idMedico = Integer.parseInt(session.getSessionID());
			System.out.println(idMedico);
			//Apanhar as Consultas
			List<Consulta> cu = consultaDao.findWithDateMedico(idMedico);
			//--------------------
			//Apanhar os Utentes do medico
			List<Utente> ut = utenteDao.findByMedico(idMedico);
			//----------------------------
			//Apanhar as Medicacoes para renovar
			List<Medicacao> medicacaoRows = medicacaoDao.findByMedicoPendente(idMedico);
			//----------------------------------
			//Apanhar os Exames todos ordem por data
			List<Exame> exames = exameDao.findAllByMedico(idMedico);
			//----------------------------------
			mav.setViewName("main_menu_medico");
			mav.addObject("listaParaTratar", cu);
			mav.addObject("listaUtentes",ut);
			mav.addObject("username", username);
			mav.addObject("medicacao", medicacaoRows);
			mav.addObject("exames", exames);
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
	public ModelAndView consultarDados(HttpServletRequest request,@ModelAttribute(value="utente") String utente){
		ModelAndView mav = new ModelAndView();
		String token = getSessaoToken(request);
		if(verifyLogin(token)){
			Sessao session = sessaoDao.getSessao(token);
			String username = (String) session.getSessionName();
			System.out.println("MEDICO: "+username);
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
	
	public String getSessaoToken(HttpServletRequest request){
		String sessionToken = "empty";
		System.out.println("Token de sessao antes: "+sessionToken);
		Cookie[] listaCookies = request.getCookies();
		if(listaCookies != null){
			for(Cookie c:listaCookies){
				if(c.getName().equals("sessionToken")){
					sessionToken = c.getValue();
				}
			}
		}
		System.out.println("Esta e a cookie: "+ sessionToken);
		return sessionToken;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		try{
			Sessao session = sessaoDao.getSessao(getSessaoToken(request));
			sessaoDao.removerSessao(getSessaoToken(request));
			Cookie cookie = new Cookie("sessionToken", "empty");
			response.addCookie(cookie);
			mav.setViewName("medico_inicio");
			return mav;
		}
		catch(Exception e){
			e.printStackTrace();
			mav.setViewName("redirect:/medico");
			return mav;
		}
	}
	
	public boolean verifyLogin(String sessionToken){
		if(sessionToken.equals("empty")){
			System.out.println("NAO TEM SESSAO");
			return false;
		}
		else{
			Sessao session = sessaoDao.getSessao(sessionToken);
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
