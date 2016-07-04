package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.AlturaDao;
import org.jboss.tools.example.springmvc.data.CirurgiaDao;
import org.jboss.tools.example.springmvc.data.ColesterolDao;
import org.jboss.tools.example.springmvc.data.ContratoMedicoDao;
import org.jboss.tools.example.springmvc.data.GlicemiaDao;
import org.jboss.tools.example.springmvc.data.GuardiaoDao;
import org.jboss.tools.example.springmvc.data.INRDao;
import org.jboss.tools.example.springmvc.data.InstituicaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.MedicoUtenteDao;
import org.jboss.tools.example.springmvc.data.PesoDao;
import org.jboss.tools.example.springmvc.data.SaturacaoO2Dao;
import org.jboss.tools.example.springmvc.data.TensaoArterialDao;
import org.jboss.tools.example.springmvc.data.TrigliceridosDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/perfil")
public class PerfilController {
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private MedicoUtenteDao muDao;
	
	@Autowired
	private InstituicaoDao insDao;
	
	@Autowired
	private MedicoDao medicoDao;
	
	
	
	private AuthController as= new AuthController();
	

	@RequestMapping(value="/dados")
    public ModelAndView goToPerfilStatic(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    	ModelAndView mav = new ModelAndView();
    	if(verifyLogin(session)){
    	//if(true){

    		mav.setViewName("perfil");
    		
    		
	    	String username = (String) session.getAttribute("sessionID");
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
	    	
	    	mav.addObject("username", currentUser.getNome());
	    	mav.addObject("utente", currentUser.getNumUtente());
	    	mav.addObject("cc", currentUser.getCc());
	    	mav.addObject("mail", currentUser.getEmail());
	    	mav.addObject("nif", currentUser.getNif());
	    	mav.addObject("centro_saude", insDao.findById(currentUser.getCentroSaude()).getNome());
	    	if(currentUser.getMedico() == 0){
	    		mav.addObject("medico", "Não tem médico atribuído");
	    	}
	    	else{
	    		mav.addObject("medico", medicoDao.findById(currentUser.getMedico()).getNome());
	    	}
	    	
	    	mav.addObject("medicoId", currentUser.getMedico());
	    	int telemovel = currentUser.getTelemovel();
	    	int emergencia = currentUser.getContactoEmergencia();
	    	mav.addObject("telemovel", ((telemovel == 0) ?  "000000000" : telemovel));
	    	mav.addObject("emergencia", ((emergencia == 0) ? "000000000" : emergencia ));
	    	
	    	
	    	
    		/*
	    	mav.addObject("username", "Tiago");
	    	mav.addObject("utente", 123123123);
	    	mav.addObject("cc", 12345678);
	    	mav.addObject("nif", 123456789);
	    	mav.addObject("morada", "moradaBueMa");
	    	mav.addObject("mail", "mailBueMau");
	    	mav.addObject("password", "passSuperBad");
	    	mav.addObject("telemovel", 987654321);
	    	mav.addObject("emergencia", 971237421);
	    	*/
	    	
    	}
    	
    	else{
    		mav.setViewName("redirect:/index");
    	}
    	
    	return mav;
    }
	
	@RequestMapping(value="/actualizar")
    public ModelAndView goToPerfilChange(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    	ModelAndView mav = new ModelAndView();
    	if(verifyLogin(session)){
    	//if(true){

    		mav.setViewName("alterarPerfil");
    		
    		
	    	String username = (String) session.getAttribute("sessionID");
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
	    	
	    	mav.addObject("username", currentUser.getNome());
	    	mav.addObject("utente", currentUser.getNumUtente());
	    	mav.addObject("cc", currentUser.getCc());
	    	mav.addObject("mail", currentUser.getEmail());
	    	mav.addObject("nif", currentUser.getNif());
	    	mav.addObject("centro_saude", insDao.findById(currentUser.getCentroSaude()).getNome());
	    	mav.addObject("medico", medicoDao.findById(currentUser.getMedico()).getNome());
	    	mav.addObject("medicoId", currentUser.getMedico());
	    	int telemovel = currentUser.getTelemovel();
	    	int emergencia = currentUser.getContactoEmergencia();
	    	mav.addObject("telemovel", ((telemovel == 0) ?  "000000000" : telemovel));
	    	mav.addObject("emergencia", ((emergencia == 0) ? "000000000" : emergencia ));
	    	
	    	
	    	
    		/*
	    	mav.addObject("username", "Tiago");
	    	mav.addObject("utente", 123123123);
	    	mav.addObject("cc", 12345678);
	    	mav.addObject("nif", 123456789);
	    	mav.addObject("morada", "moradaBueMa");
	    	mav.addObject("mail", "mailBueMau");
	    	mav.addObject("password", "passSuperBad");
	    	mav.addObject("telemovel", 987654321);
	    	mav.addObject("emergencia", 971237421);
	    	*/
	    	
    	}
    	
    	else{
    		mav.setViewName("redirect:/index");
    	}
    	
    	return mav;
    }
	
	@RequestMapping(value = "/atualizarPerfil", method = RequestMethod.POST)
	@ResponseBody
	public List<String> atualizarPerfil(@RequestParam(value="morada") String morada,
											@RequestParam(value="mail") String mail,
												@RequestParam(value = "telemovel") String telemovel, @RequestParam(value = "emergencia") String emergencia,
													@RequestParam(value="oldPass") String oldPass, @RequestParam(value="newPass") String newPass,
														@RequestParam(value="confirmNewPass") String confirmNewPass, HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		List<String> finalmsg= new ArrayList<String>();
		if(verifyLogin(session)){
			String username = (String) session.getAttribute("sessionID");
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
			try{
				
				//----------MORADA------------
				System.out.println(morada);
				if(currentUser.getMorada() != morada){
					currentUser.setMorada(morada);
				}
				System.out.println(currentUser.getMorada());
				
				//----------EMAIL-------------
				System.out.println(mail);
				if(mail.length() <= 0){
					throw new BadRegistException("Por favor insira um email", "mail");
				}
				if(!as.verifyEmail(mail)){
					throw new BadRegistException("Por favor insira um email válido", "mail");
				}
				else{
					if(currentUser.getEmail() != mail){
						currentUser.setEmail(mail);
					}
				}
				System.out.println(currentUser.getEmail());
				//-------TELEMOVEL-----------
				System.out.println(telemovel);
				int telemovelInt = Integer.parseInt(telemovel);
				if(currentUser.getTelemovel() != telemovelInt){
					currentUser.setTelemovel(telemovelInt);
				}
				System.out.println(currentUser.getTelemovel());
				
				//-------EMERGENCIA----------
				System.out.println(emergencia);
				int emergenciaInt = Integer.parseInt(emergencia);
				if(currentUser.getContactoEmergencia() != emergenciaInt){
					currentUser.setContactoEmergencia(emergenciaInt);
				}
				System.out.println(currentUser.getContactoEmergencia());
				//-------PASSWORDS-----------
				System.out.println(oldPass);
				System.out.println(newPass);
				System.out.println(confirmNewPass);
				
				String hashOld = HashTextTest.sha256(oldPass);
				String hashNew = HashTextTest.sha256(newPass);
				String hashConfirm = HashTextTest.sha256(confirmNewPass);
				if(currentUser.getPassword().equals(hashOld)){
					if(hashNew.equals(hashConfirm)){
						currentUser.setPassword(hashNew);
					}
				}
				
				utenteDao.updateUtente(currentUser);
				System.out.println("COMECA AQUI");
				System.out.println(currentUser.getMorada());
				System.out.println(currentUser.getContactoEmergencia());
				System.out.println(currentUser.getEmail());
				System.out.println(currentUser.getPassword());
				System.out.println(currentUser.getTelemovel());
				System.out.println("ACABA AQUI");
				finalmsg.add("true");
				finalmsg.add("control");
				return finalmsg;
		    }
			catch(Exception e){ 
				System.out.println(e);
				}
			}
		else{
			finalmsg.add("false");
			finalmsg.add("control");
			return finalmsg;
		}
		finalmsg.add("false");
		finalmsg.add("control");
		return finalmsg;
	}
	
	public boolean verifyLogin(HttpSession session) {
		if(session.getAttribute("sessionID") == null){
			return false;
		}
		else{
			try {
				if(utenteDao.verifyActivatedUser((String)session.getAttribute("sessionID")))
				return true;
				else{
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
		return false;
		
	}
	
	@RequestMapping(value="/mudarPrivacidade", method = RequestMethod.POST, params={"tipo", "booleano"})
	@ResponseBody
	public boolean mudarPrivacidade(HttpSession session, @RequestParam(value="tipo") String tipo, @RequestParam(value="booleano") String booleano) {
		if (verifyLogin(session)) {
			MedicoUtente mu = muDao.findByUtente((String) session.getAttribute("sessionID"));
			System.out.println("tipo= " + tipo);
			System.out.println("booleano = " + booleano);
		switch (tipo) {
		case "Altura" :
			if (booleano.equals("true")){
				muDao.setAltura(mu,true);
			}
			else {
				muDao.setAltura(mu,false);
			}
			break;
		case "Glicemia" :
			if (booleano.equals("true")){
				muDao.setGlicemia(mu, true);
			}
			else {
				muDao.setGlicemia(mu, false);
			}
			break;
		case "Colesterol":
			if (booleano.equals("true")){
				muDao.setColesterol(mu, true);
			}
			else {
				muDao.setColesterol(mu, false);
			}
			break;
		case "INR":
			if (booleano.equals("true")){
				muDao.setInr(mu, true);
			}
			else {
				muDao.setInr(mu, false);
			}
			break;
		case "Peso":
			if (booleano.equals("true")){
				muDao.setPeso(mu, true);
			}
			else {
				muDao.setPeso(mu, false);
			}
			break;
		case "SaturacaoO2":
			if (booleano.equals("true")){
				muDao.setSaturacao(mu, true);
			}
			else {
				muDao.setSaturacao(mu, false);
			}
			break;
		case "Trigliceridos":
			if (booleano.equals("true")){
				muDao.setTrigliceridos(mu, true);
			}
			else {
				muDao.setTrigliceridos(mu, false);
			}
			break;
		case "TensaoArterial":
			if (booleano.equals("true")){
				muDao.setTensao(mu, true);
			}
			else {
				muDao.setTensao(mu, false);
			}
			break;
		}
		return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value="/verPrivacidades")
	public ModelAndView verPrivacidades (HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("privacidade");
		MedicoUtente mu = muDao.findByUtente((String) session.getAttribute("sessionID"));
		List<MedicoUtente> lista = new ArrayList<MedicoUtente>();
		lista.add(mu);
		System.out.println(lista);
		mav.addObject("mu", lista);
		return mav;
	}
}







