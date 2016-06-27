package org.jboss.tools.example.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.tools.example.springmvc.sensitivedata.Utente;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Cifras {

	
	public static String encrypt(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		SecretKey key = getKey();
		
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(msg.getBytes());
        String encrypted = new BASE64Encoder().encode(encVal);
		return encrypted;
	}
	
	public static SecretKey getKey() throws IOException, NoSuchAlgorithmException {
		System.out.println(Utente.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String rootPath = System.getProperty("jboss.server.config.dir"); 
		File f = new File(rootPath+ File.separator+ "notakey.key");
		if (!f.exists()) {
			System.out.println("criei nova key");
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
		
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		byte[] key = new byte[16];
		ois.read(key);
		SecretKey key2 = new SecretKeySpec(key, 0, key.length, "AES");
		return key2;
	}
	
	public static String decrypt(String encrypted) throws NoSuchAlgorithmException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecretKey key = getKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] dec = new BASE64Decoder().decodeBuffer(encrypted);
		byte[] str = c.doFinal(dec);
		return new String(str, "utf-8");
	}
}
