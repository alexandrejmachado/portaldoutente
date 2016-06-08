package org.jboss.tools.example.springmvc.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.data.MedicacaoDao;
import org.jboss.tools.example.springmvc.data.MedicamentoDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/medicacao")
public class MedicacaoController {
	
	@Autowired
	public MedicamentoDao medDao;
	
	@Autowired
	public UtenteDao utDao;
	
	@Autowired
	public MedicacaoDao medicacaoDao;
	
	
	
	@RequestMapping(value="/inserir")
	public void inserirMedicacao(HttpSession session, String nomeMedicamento, double dosagemDiaria, String indicacoes, String renovacao) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Medicamento med = medDao.findByNome(nomeMedicamento);
		medicacaoDao.novaMedicacao(Integer.parseInt((String) session.getAttribute("sessionID")), med.getId(), dosagemDiaria, indicacoes, renovacao, med.getComprimidos());
	}
	
	@RequestMapping(value="/verificar")
	public void verificarMedicacao(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		medicacaoDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID"))); 
	}

}
