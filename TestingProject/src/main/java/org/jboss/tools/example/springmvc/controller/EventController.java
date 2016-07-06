package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.InstituicaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.SessaoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Consulta;
import org.jboss.tools.example.springmvc.model.Sessao;
import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;
import org.jboss.tools.example.springmvc.sensitivedata.Medico;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jboss.tools.example.springmvc.controller.MesesConverter;

import org.jboss.tools.example.springmvc.controller.Cifras;

@Controller
@RequestMapping(value="/calendario")
public class EventController {
	
	@Autowired
	private MesesConverter mConv;
	
	@Autowired
	private MedicoDao medicoDao;
	
	@Autowired
	private ConsultaDao consultaDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private InstituicaoDao insDao;
	
	@Autowired
	private UtenteDao utenteDao;

	@RequestMapping(value="/getEventos")
	@ResponseBody
	public ArrayList<Object> getEventos(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		String token = getSessaoToken(request);
		if(verifyLogin(token)){
			Sessao session = sessaoDao.getSessao(token);
			System.out.println("session of: "+session.getSessionID());
			int numUtente = Integer.parseInt(session.getSessionID());
			Utente curUtente = utenteDao.findUtenteById(numUtente);
			List<Consulta> all = consultaDao.findAllByUtente(numUtente, curUtente.getCentroSaude());
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
		else{
			ArrayList<Object> lista= new ArrayList<Object>();
			lista.add(0, "STATUS: Unauthorized Access");
			return lista;
		}
	}
	
	@RequestMapping(value="/view")
	public ModelAndView calendarView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String token = getSessaoToken(request);
		if(verifyLogin(token)){
			Sessao session = sessaoDao.getSessao(token);
			mav.addObject("username", session.getSessionName());
			mav.setViewName("calendarView");
			mav.addObject("data", new Date().getTime());
		}
		else{
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	@RequestMapping(value="/marcarConsultaView", method = RequestMethod.GET, params={"data"})
	public ModelAndView marcarConsultaView(@RequestParam(value = "data") String data, HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		String token = getSessaoToken(request);
		Sessao session = sessaoDao.getSessao(token);
		//-----------------------------------------
		int numUtente = Integer.parseInt(session.getSessionID());
		//int numUtente = 123123123;
		Utente curUtente = utenteDao.findUtenteById(numUtente);
		List<Consulta> all = consultaDao.findWithDate(numUtente, curUtente.getCentroSaude());
		//List<Consulta> all = consultaDao.findWithDate(numUtente,1);
		//-----------------------------------------
		ModelAndView mav = new ModelAndView();
		Date temp = new Date(Long.parseLong(data));
		String[] tempD = temp.toString().split(" ");
		
		// data do dia que foi clicado
		String out = tempD[5] + "-"+ mConv.getMesByName(tempD[1])+"-"+ tempD[2];
		if(all.size() == 0){
			mav.setViewName("consulta_prompt");
			mav.addObject("data", out);
			//----------------------------------------
			ArrayList<String> lista = Horarios.getHorarios();
			List<Consulta> conList = consultaDao.findByDate(data,curUtente.getCentroSaude());
			for(Consulta c : conList){
				System.out.println(c.getData().toString().substring(11, 16));
				lista.remove(c.getData().toString().substring(11, 16));
			}
			
			
			mav.addObject("lista", lista);
			//----------------------------------------
			return mav;
		}
		else{
			String data1 = all.get(0).getData().toString().split(" ")[0];
			String data2 = new java.sql.Timestamp(temp.getTime()).toString().split(" ")[0];
			if(data1.equals(data2)){
				// enviar info sobre a consulta
				Consulta currentConsulta = consultaDao.findByUtenteAndData(numUtente,data);
				// ALERTA: NAO TESTAR COM MEDICO INESISTENTE
				Medico m = medicoDao.findById(currentConsulta.getIdMedico());
				int consultaId = currentConsulta.getId();
				Instituicao inst = insDao.findById(currentConsulta.getIdInstituicao());
				if(currentConsulta.isConfirmada()){
					mav.addObject("confirmada", "A sua consulta está confirmada!");
				}
				else{
					mav.addObject("confirmada", "A sua consulta ainda não está confirmada!");
				}
				mav.addObject("data", currentConsulta.getData());
				mav.addObject("consultaId", consultaId);
				mav.addObject("medico", m.getNome());
				mav.addObject("sala", currentConsulta.getSala());
				mav.addObject("centroSaude", inst.getNome());
				mav.setViewName("desmarcar_consulta");
				return mav;
			}
			else{
				// informar que nao pode marcar consulta para outro dia
				mav.setViewName("erro_consulta");
				return mav;
			}
		}
		
	}
	
	@RequestMapping(value="/persistirConsulta", method = RequestMethod.POST,params={"data", "obs"})
	@ResponseBody
	public boolean persistirConsulta(@RequestParam(value="data") Date data, @RequestParam(value="obs") String obs, 
									  HttpServletRequest request) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		String token = getSessaoToken(request);
		Sessao session = sessaoDao.getSessao(token);
		int numUtente = Integer.parseInt(session.getSessionID());
		Utente curUtente = utenteDao.findUtenteById(numUtente);
		//TODO maybe falta por lock quando medico == 0?????
		int idMedico = curUtente.getMedico();
		Random ran = new Random();
		int x = ran.nextInt(31);
		//Consulta cons = consultaDao.novo(idMedico, numUtente, curUtente.getCentroSaude(), "amarela", data, obs);
		consultaDao.novo(idMedico, numUtente, curUtente.getCentroSaude(),Integer.toString(x), data, obs, curUtente.getNome());
		//consultaDao.confirmarConsulta(cons);
		return true;
		}
	
	@RequestMapping(value="/removerConsulta", method = RequestMethod.POST, params={"consultaId"})
	@ResponseBody
	public boolean removerConsulta(@RequestParam(value="consultaId") String consultaId){
		try{
			System.out.println(consultaId);
			consultaDao.remove(Integer.parseInt(consultaId));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean verifyLogin(String sessionToken) {
		System.out.println(sessionToken);
		System.out.println("A VERIFICAR SE ESTA LOGADO:");
		if(sessionToken.equals("empty")){
			System.out.println("NAO TEM SESSAO");
			return false;
		}
		else{
			System.out.println("antes");
			Sessao session = sessaoDao.getSessao(sessionToken);
			System.out.println("depois");
			System.out.println(session);
			try {
				System.out.println("VERIFICAR SE ESTA ACTIVA A CONTA");
				if(utenteDao.verifyActivatedUser(session.getSessionID())){System.out.println("ENCONTREI");
					return true;}
				else{
					System.out.println("NAO ESTA ACTIVA A CONTA");
					sessaoDao.removerSessao(sessionToken);
				return false;
				}
			} catch (InvalidKeyException e) {
				
				e.printStackTrace();
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				
				e.printStackTrace();
			} catch (BadPaddingException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			catch(NullPointerException e){
				return false;
			}
		}
		System.out.println("ERRO ESTRANHO");
		return false;
		
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

}







