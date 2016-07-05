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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.jboss.tools.example.springmvc.data.InstituicaoDao;
import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoIdDao;
import org.jboss.tools.example.springmvc.data.SessaoDao;
import org.jboss.tools.example.springmvc.data.MedicoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Medicacao;
import org.jboss.tools.example.springmvc.model.Medicamento;
import org.jboss.tools.example.springmvc.model.Medicamentoid;
import org.jboss.tools.example.springmvc.model.Sessao;
import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;
import org.jboss.tools.example.springmvc.sensitivedata.Medico;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jboss.tools.example.springmvc.data.UtenteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	private SessaoDao sessaoDao;
	
	
	@Autowired
	public MedicamentoDao medDao;
	
	@Autowired
	public UtenteDao utDao;
	
	@Autowired
	public MedicoDao medicoDao;
	
	@Autowired
	public InstituicaoDao instDao;
	
	@Autowired
	public MedicacaoDao medicacaoDao;
	
	@Autowired
	public MedicamentoIdDao medidDao;
	
	public HashMap<String,String> map = new HashMap<String, String>();

	
	@RequestMapping(value="/inserir", method = RequestMethod.POST,params={"nome", "dosagem", "indicacoes"})
	@ResponseBody
	public boolean inserirMedicacao(HttpServletRequest request, @RequestParam(value="nome") String nomeMedicamento, @RequestParam(value="dosagem") double dosagemDiaria, @RequestParam(value="indicacoes") String indicacoes) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("cheguei aqui");
		System.out.println("tentei corresponder o medicamento:" + nomeMedicamento);
		Medicamentoid medid= medidDao.findByNome(nomeMedicamento);
		
		String token = getSessaoToken(request);
		Sessao session = sessaoDao.getSessao(token);
		
		int id = medid.getID();
		Medicamento med = medDao.findById(id);
		System.out.println("medicamento id: " + med.getId());
		System.out.println("medicamento comprimidos: " + med.getComprimidos());
		if(medicacaoDao.exists(Integer.parseInt(session.getSessionID()), med.getId())){
			return false;
		}
		else {
			Utente curUtente = utDao.findUtenteById(Integer.parseInt(session.getSessionID()));
	
			medicacaoDao.novaMedicacao(Integer.parseInt(session.getSessionID()), med.getId(), nomeMedicamento, dosagemDiaria, indicacoes, "Pendente", med.getComprimidos(), curUtente.getMedico(), curUtente.getNome());
			return true;
		}
	}
	
	
	@RequestMapping(value="/obterMedicacoes")
	@ResponseBody
	public List<Medicacao> obterMedicacao(HttpServletRequest request) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		String token = getSessaoToken(request);
		Sessao session = sessaoDao.getSessao(token);
		List<Medicacao> lista = medicacaoDao.findAllByUtente(Integer.parseInt(session.getSessionID())); 
		return lista;
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
				if(utenteDao.verifyActivatedUser(session.getSessionID())){System.out.println("VERFICAR SE ESTA ACTIVA A CONTA");
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
		}
		System.out.println("ERRO ESTRANHO");
		return false;
		
	}		
	
	
	@RequestMapping(value = "/view")
	public ModelAndView verificar(HttpServletRequest request) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		ModelAndView mav = new ModelAndView();
		
		String token = getSessaoToken(request);
		if(verifyLogin(token)){
			Sessao session = sessaoDao.getSessao(token);
			List<Medicacao> lista = medicacaoDao.findAllByUtente(Integer.parseInt((String) session.getSessionID()));
			mav.addObject("lista", lista);
			mav.addObject("username", session.getSessionName());
			mav.setViewName("medicamentos");
		}
		else {
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	

	@RequestMapping(value = "/renovar", method = RequestMethod.POST,params={"id"})
	@ResponseBody
	public boolean renovarMed(HttpServletRequest request, @RequestParam("id") String id) {
		System.out.println("im here");
		return medicacaoDao.renovarMed(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/apagar", method = RequestMethod.POST, params={"id"})
	@ResponseBody
	public boolean apagarMed(HttpServletRequest request, @RequestParam("id") String id) {
		return medicacaoDao.deleteMedicacao(Integer.parseInt(id));
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
	

	
	
	@RequestMapping(value="/verReceita", method = RequestMethod.POST, params={"medicacaoID"})
	@ResponseBody
	public ModelAndView verReceita(HttpServletRequest request, @RequestParam("medicacaoID") int id) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		ModelAndView mav = new ModelAndView();
		String token = getSessaoToken(request);
		Sessao session = sessaoDao.getSessao(token);
		mav.setViewName("receita");
		Utente ut = utDao.findUtenteById(Integer.parseInt(session.getSessionID()));
		mav.addObject("utenteName", ut.getNome());
		mav.addObject("utenteID", ut.getNumUtente());
		mav.addObject("utenteTelemovel", ut.getTelemovel());
		int idMedicacao = id;
		Medicacao med = medicacaoDao.findById(idMedicacao);
		mav.addObject("nomeMedicamento", med.getNomeMedicamento());
		double dose = med.getDose();
		mav.addObject("dose", dose);
		if (dose == 1) {
			mav.addObject("extenso", "um");
		}
		else if (dose == 2) {
			mav.addObject("extenso", "dois");
		}
		else if (dose == 3) {
			mav.addObject("extenso", "tres");
		}
		Instituicao inst = instDao.findById(ut.getCentroSaude());
		mav.addObject("medicacaoID", med.getId());
		mav.addObject("instituicao", inst.getNome());
		Medico medico = medicoDao.findById(ut.getMedico());
		mav.addObject("nomeMedico", medico.getNome());
		mav.addObject("contactoInstituicao", inst.getTelefone());
		mav.addObject("data", new Date());
		return mav;
	}



}
