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
	
	@NotNull
	private String password;
	
	private String email;
	
	private String telemovel;
	
	@NotNull
	private String nome;
	
	private int contactoEmergencia;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@NotNull
	private String nif;
	
	private boolean verified = false;
	
	
	public Utente() {}
	
//	public Utente(int username, String password) {
//		numUtente = username;
//		this.password = password;
//		nif = 1234;
//		nome = "Tiago e parvo";
//		dataNascimento = new Date();
//		
//	}
	
	public Utente(String username, int numUtente, int cc, String morada, String mail, String password, int telemovel, int nif, String code, String codeSms) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		this.numUtente = encrypt(Integer.toString(numUtente));
		this.password = password;
		this.nome = username;
		this.cc = encrypt(Integer.toString(cc));
		this.morada = morada;
		this.email = mail;
		this.telemovel = encrypt(Integer.toString(telemovel));
		this.nif = encrypt(Integer.toString(nif));;
		dataNascimento = new Date();
		verificationCode = code;
		verificationCodeSms = codeSms;
		System.out.println("NIF = " + getNif());
	}
	
	public int getNumUtente() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(decrypt(numUtente));
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
		return Integer.parseInt(decrypt(telemovel));
	}

	public void setTelemovel(int telemovel) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		this.telemovel = encrypt(Integer.toString(telemovel));
	}

	public String getNome() {
		return nome;
	}

	public int getContactoEmergencia() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(decrypt(Integer.toString(contactoEmergencia)));
	}

	public void setContactoEmergencia(int contactoEmergencia) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		this.contactoEmergencia = Integer.parseInt(encrypt(Integer.toString(contactoEmergencia)));
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public int getNif() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return Integer.parseInt(decrypt(nif));
	}
	
	public boolean verifyClient(String code) {
		if (verificationCode.equals(code)) {
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
	
	public String encrypt(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		SecretKey key = getKey();
		
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(msg.getBytes());
        String encrypted = new BASE64Encoder().encode(encVal);
		return encrypted;
	}
	
	public SecretKey getKey() throws IOException, NoSuchAlgorithmException {
		System.out.println(Utente.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		File f = new File("notakey.key");
		if (!f.exists()) {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128);
			SecretKey key3 = kg.generateKey();
			FileOutputStream fos = new FileOutputStream("notakey.key");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			byte[] keyByte = key3.getEncoded();
			oos.write(keyByte);
			oos.flush();
			fos.flush();
			oos.close();
			fos.close();
		}
		
		FileInputStream fis = new FileInputStream("notakey.key");
		ObjectInputStream ois = new ObjectInputStream(fis);
		byte[] key = new byte[16];
		ois.read(key);
		SecretKey key2 = new SecretKeySpec(key, 0, key.length, "AES");
		return key2;
	}
	
	public String decrypt(String encrypted) throws NoSuchAlgorithmException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecretKey key = getKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] dec = new BASE64Decoder().decodeBuffer(encrypted);
		byte[] str = c.doFinal(dec);
		return new String(str, "utf-8");
	}
}
