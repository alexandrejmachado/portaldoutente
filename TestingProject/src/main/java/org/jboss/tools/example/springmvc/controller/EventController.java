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
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Consulta;
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
	private ConsultaDao consultaDao;
	
	@Autowired
	private UtenteDao utenteDao;

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
		mav.addObject("data", new Date().getTime());
		return mav;
	}
	
	@RequestMapping(value="/marcarConsultaView", method = RequestMethod.GET, params={"data"})
	public ModelAndView marcarConsultaView(@RequestParam(value = "data") String data, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//TODO meter o lock de sessao
		
		//-----------------------------------------
		//int numUtente = (int) session.getAttribute("sessionID");
		int numUtente = 123123123;
		Utente curUtente = utenteDao.findUtenteById(numUtente);
		//List<Consulta> all = consultaDao.findWithDate(numUtente, numUtente.getCentroSaude());
		List<Consulta> all = consultaDao.findWithDate(numUtente,1);
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
			List<Consulta> conList = consultaDao.findByDate(data,1);
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
				int consultaId = currentConsulta.getId();
				mav.addObject("data", currentConsulta.getData());
				mav.addObject("consultaId", consultaId);
				mav.setViewName("cenas");
				return mav;
			}
			else{
				// informar que nao pode marcar consulta para outro dia
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
		System.out.println("ESTOU AQUI DENTRO");
		consultaDao.novo(13, Integer.parseInt(numUtente), Integer.parseInt(instituicao), "amarela", data, obs);
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
	

}







