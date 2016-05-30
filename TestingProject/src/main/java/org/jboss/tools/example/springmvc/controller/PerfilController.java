package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/perfil")
public class PerfilController {
	
	@Autowired
	private UtenteDao utenteDao;
	
	private AuthController as= new AuthController();
	
	@Autowired
	private EntityManager em;

	@RequestMapping(value="")
    public ModelAndView goToPerfil(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    	ModelAndView mav = new ModelAndView();
    	
    	//if(verifyLogin(session)){
    	if(true){
    	
    		mav.setViewName("perfil");
    	
    		/*
	    	String username = (String) session.getAttribute("sessionID");
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
	    	
	    	mav.addObject("username", currentUser.getNome());
	    	mav.addObject("utente", currentUser.getNumUtente());
	    	mav.addObject("cc", currentUser.getCc());
	    	mav.addObject("mail", currentUser.getEmail());
	    	mav.addObject("nif", currentUser.getNif());
	    	int telemovel = currentUser.getTelemovel();
	    	int emergencia = currentUser.getContactoEmergencia();
	    	mav.addObject("telemovel", ((telemovel == 0) ?  "000000000" : telemovel));
	    	mav.addObject("emergencia", ((emergencia == 0) ? "000000000" : emergencia ));
	    	*/
	    	
    		
	    	mav.addObject("username", "Tiago");
	    	mav.addObject("utente", 123123123);
	    	mav.addObject("cc", 12345678);
	    	mav.addObject("nif", 123456789);
	    	mav.addObject("morada", "moradaBueMa");
	    	mav.addObject("mail", "mailBueMau");
	    	mav.addObject("password", "passSuperBad");
	    	mav.addObject("telemovel", 987654321);
	    	mav.addObject("emergencia", 971237421);
	    	
	    	
    	}
    	/*
    	else{
    		mav.setViewName("redirect:/index");
    	}
    	*/
    	
    	return mav;
    }
	
	@RequestMapping(value = "/atualizarPerfil", method = RequestMethod.POST)
	public ModelAndView atualizarPerfil(@RequestParam(value="morada") String morada,
											@RequestParam(value="mail") String mail,
												@RequestParam(value = "telemovel") String telemovel, @RequestParam(value = "emergencia") String emergencia,
													@RequestParam(value="oldPass") String oldPass, @RequestParam(value="newPass") String newPass,
														@RequestParam(value="confirmNewPass") String confirmNewPass, HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		//if(verifyLogin(session)){
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
			if(currentUser.getTelemovel() != emergenciaInt){
				currentUser.setTelemovel(emergenciaInt);
			}
			System.out.println(currentUser.getContactoEmergencia());
			//-------PASSWORDS-----------
			System.out.println(oldPass);
			System.out.println(newPass);
			System.out.println(confirmNewPass);
			
			String hashOld = HashTextTest.sha256(oldPass);
			String hashNew = HashTextTest.sha256(newPass);
			String hashConfirm = HashTextTest.sha256(confirmNewPass);
			if(currentUser.equals(hashOld)){
				if(hashNew.equals(hashConfirm)){
					currentUser.setPassword(hashNew);
				}
			}
		}
		catch(Exception e){
			return null;
		}
		
		//}
		/*
    	else{
    		mav.setViewName("redirect:/index");
    	}
    	*/
		return null;
	}
}







