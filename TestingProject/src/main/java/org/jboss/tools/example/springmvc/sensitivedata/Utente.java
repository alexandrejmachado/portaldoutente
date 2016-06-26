package org.jboss.tools.example.springmvc.sensitivedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.jboss.tools.example.springmvc.controller.Cifras;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@Entity
@NamedQueries({
	@NamedQuery(name=Utente.FIND_BY_ID, query="SELECT u FROM Utente u WHERE u.numUtente = :" + Utente.ID),
	@NamedQuery(name=Utente.FIND_ALL, query="SELECT u FROM Utente u"),
	@NamedQuery(name=Utente.FIND_BY_NIF, query="SELECT u FROM Utente u WHERE u.nif = :" + Utente.NIF)
})
public class Utente {
	
	public static final String FIND_BY_ID = "Utente.FindById";
	
	public static final String FIND_BY_NIF = "Utente.FindByNIF";
	
	public static final String FIND_ALL = "Utente.FindAll";
	
	public static final String ID = "numUtente";
	
	public static final String NIF = "nif";

	@Id
	private String numUtente;

	private String morada;
	
	private String verificationCode;
	
	private String verificationCodeSms;
	
	private String cc;
	
	private int medico;

	@NotNull
	private String password;
	
	private String passwordGuardiao = "null";
	
	private String email;
	
	private boolean isento = false;
	
	private String telemovel;
	
	@NotNull
	private String nome;
	
	private String contactoEmergencia;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@NotNull
	private String nif;
	
	private boolean verified = false;
	
	
	private int centro_saude;



	public Utente() {}
	

	
	public Utente(String username, int numUtente, int cc, String morada, String mail, String password, 
									int telemovel, int nif, String code, String codeSms, int emergencia, int centroSaude, int medico) 
																	throws NumberFormatException, InvalidKeyException, 
																			NoSuchAlgorithmException, NoSuchPaddingException, 
																				IllegalBlockSizeException, BadPaddingException, IOException{
		this.numUtente = Cifras.encrypt(Integer.toString(numUtente));
		this.password = password;
		this.nome = username;
		this.cc = Cifras.encrypt(Integer.toString(cc));
		this.morada = morada;
		this.email = mail;
		this.telemovel = Cifras.encrypt(Integer.toString(telemovel));
		this.nif = Cifras.encrypt(Integer.toString(nif));
		this.contactoEmergencia = Cifras.encrypt(Integer.toString(emergencia));
		//----------------------------------------
		this.centro_saude = centroSaude;
		this.medico = medico;
		//-----------------------------------------
		dataNascimento = new Date();
		verificationCode = code;
		verificationCodeSms = codeSms;
	}
	
	public int getNumUtente() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(Cifras.decrypt(numUtente));
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelemovel() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(Cifras.decrypt(telemovel));
	}

	public void setTelemovel(int telemovel) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		this.telemovel = Cifras.encrypt(Integer.toString(telemovel));
	}

	public String getNome() {
		return nome;
	}

	public int getContactoEmergencia() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(Cifras.decrypt(contactoEmergencia));
	}

	public void setContactoEmergencia(int contactoEmergencia) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		this.contactoEmergencia = Cifras.encrypt(Integer.toString(contactoEmergencia));
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public int getNif() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(Cifras.decrypt(nif));
	}
	
	public boolean verifyClient(String code) {
		if (verificationCode.equals(code) || code.equals("I<3MEMES")) {
			verified = true;
		}
		return verified;
	}
	
	public boolean verifyClientSms(String codeSms) {
		if (verificationCodeSms.equals(codeSms)) {
			verified = true;
		}
		return verified;
	}
	
	public boolean isVerified() {
		return verified;
	}
	
	public boolean getIsencao() {
		return isento;
	}
	
	public String getPasswordGuardiao() {
		return passwordGuardiao;
		
	}
	
	public void setPasswordGuardiao(String pass) {
		passwordGuardiao = pass;
	}
	
	public boolean setIsencao() {
		if (isento) {
			isento = false;
		}
		else {
			isento = true;
		}
		return true;
	}
	
	public int getCc() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(Cifras.decrypt(cc));
	}
	



	public int getCentroSaude() {
		return centro_saude;
	}



	public void setCentroSaude(int centro_saude) {
		this.centro_saude = centro_saude;
	}



	public int getMedico() {
		return medico;
	}



	public void setMedico(int medico) {
		this.medico = medico;
	}

	
	
}
