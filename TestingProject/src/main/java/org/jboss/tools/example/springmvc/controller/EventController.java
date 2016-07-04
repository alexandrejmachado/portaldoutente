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
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.InstituicaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Consulta;
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
	private InstituicaoDao insDao;
	
	@Autowired
	private UtenteDao utenteDao;

	@RequestMapping(value="/getEventos")
	@ResponseBody
	public ArrayList<Object> getEventos(HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		if(verifyLogin(session)){
			System.out.println("session of: "+session.getAttribute("sessionID"));
			int numUtente = Integer.parseInt((String)session.getAttribute("sessionID"));
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
	public ModelAndView calendarView(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("calendarView");
			mav.addObject("data", new Date().getTime());
		}
		else{
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	@RequestMapping(value="/marcarConsultaView", method = RequestMethod.GET, params={"data"})
	public ModelAndView marcarConsultaView(@RequestParam(value = "data") String data, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{

		//-----------------------------------------
		int numUtente = Integer.parseInt((String)session.getAttribute("sessionID"));
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
									  HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		int numUtente = Integer.parseInt((String)session.getAttribute("sessionID"));
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
	
	
	public boolean verifyLogin(HttpSession session) {
		System.out.println("A VERIFICAR SE ESTA LOGADO:");
		System.out.println("ID DE ACESSO:" + session.getAttribute("sessionID"));
		if(session.getAttribute("sessionID") == null){
			System.out.println("NAO TEM SESSAO");
			return false;
		}
		else{
			try {
				System.out.println("VERFICAR SE ESTA ACTIVA A CONTA");
				if(utenteDao.verifyActivatedUser((String)session.getAttribute("sessionID"))){System.out.println("VERFICAR SE ESTA ACTIVA A CONTA");
					return true;}
				else{
					System.out.println("NAO ESTA ACTIVA A CONTA");
					session.removeAttribute("sessionID");
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
		}
		System.out.println("ERRO ESTRANHO");
		return false;
		
	}

}







