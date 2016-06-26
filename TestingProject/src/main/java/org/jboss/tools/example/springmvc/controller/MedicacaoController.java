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
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.jboss.tools.example.springmvc.model.Medicamento;
import org.jboss.tools.example.springmvc.model.Medicamentoid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jboss.tools.example.springmvc.data.UtenteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public boolean inserirMedicacao(HttpSession session, @RequestParam(value="nome") String nomeMedicamento, @RequestParam(value="dosagem") double dosagemDiaria, @RequestParam(value="indicacoes") String indicacoes) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("cheguei aqui");
		
		Medicamentoid medid= medidDao.findByNome(nomeMedicamento);
		int id = medid.getID();
		Medicamento med = medDao.findById(id);
		System.out.println("medicamento id: " + med.getId());
		System.out.println("medicamento comprimidos: " + med.getComprimidos());
		if(medicacaoDao.exists(Integer.parseInt((String) session.getAttribute("sessionID")), med.getId())){
			return false;
		}
		else {
			medicacaoDao.novaMedicacao(Integer.parseInt((String) session.getAttribute("sessionID")), med.getId(), nomeMedicamento, dosagemDiaria, indicacoes, "Pendente", med.getComprimidos());
			return true;
		}
	}
	
	
	@RequestMapping(value="/obterMedicacoes")
	@ResponseBody
	public List<Medicacao> obterMedicacao(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		List<Medicacao> lista = medicacaoDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID"))); 
		return lista;
	}
	
	
	public boolean verifyLogin(HttpSession session) {
		System.out.println(session.getAttribute("sessionID"));
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
	
	@RequestMapping(value = "/view")
	public ModelAndView verificar(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			List<Medicacao> lista = medicacaoDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			session.setAttribute("lista", lista);
			mav.addObject("lista", lista);
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("medicamentos");
		}
		else {
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	

	private AuthController as= new AuthController();
	
	

	@RequestMapping(value="/view")
	public ModelAndView calendarView(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject("username", session.getAttribute("sessionName"));
		mav.setViewName("medicamentos");
		return mav;
	}


}
