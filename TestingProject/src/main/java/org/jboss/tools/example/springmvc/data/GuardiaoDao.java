package org.jboss.tools.example.springmvc.data;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.sound.midi.Synthesizer;

import org.jboss.tools.example.springmvc.sensitivedata.Guardiao;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GuardiaoDao {
	
	@Autowired
	private EntityManager em;
	
	public boolean atribuirGuardiao (Utente numUtente, Utente numGuardiao, String permissoes) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Guardiao guardiao = new Guardiao(numUtente.getNumUtente(), numGuardiao.getNumUtente());
		if (permissoes.equals("leitura")) {
			guardiao.setLeitura(true);
		}
		else if (permissoes.equals("escrita")) {
			guardiao.setLeitura(true);
			guardiao.setEscrita(true);
		}
		else {
			System.out.println("permissao errada");
			return false;
		}
		System.out.println("escrita do guardiao: " + guardiao.isEscrita());
		System.out.println("guardiao: " + guardiao.getNumeroGuardiao());
		String passwordG = numGuardiao.getPassword();
		System.out.println(passwordG);
		numUtente.setPasswordGuardiao(passwordG);
		em.merge(numUtente);
		return true;
	}

}
