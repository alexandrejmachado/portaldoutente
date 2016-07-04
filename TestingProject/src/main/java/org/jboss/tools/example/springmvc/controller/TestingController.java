package org.jboss.tools.example.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.swing.RowFilter.Entry;

import org.jboss.tools.example.springmvc.data.AlturaDao;
import org.jboss.tools.example.springmvc.data.CirurgiaDao;
import org.jboss.tools.example.springmvc.data.ColesterolDao;
import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.ContratoMedicoDao;
import org.jboss.tools.example.springmvc.data.ExameDao;
import org.jboss.tools.example.springmvc.data.GlicemiaDao;
import org.jboss.tools.example.springmvc.data.GuardiaoDao;
import org.jboss.tools.example.springmvc.data.INRDao;
import org.jboss.tools.example.springmvc.data.InstituicaoDao;
import org.jboss.tools.example.springmvc.data.MedicoUtenteDao;
import org.jboss.tools.example.springmvc.data.PesoDao;
import org.jboss.tools.example.springmvc.data.SaturacaoO2Dao;
import org.jboss.tools.example.springmvc.data.TensaoArterialDao;
import org.jboss.tools.example.springmvc.data.TrigliceridosDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Cirurgia;
import org.jboss.tools.example.springmvc.model.Exame;
import org.jboss.tools.example.springmvc.model.Glicemia;
import org.jboss.tools.example.springmvc.model.MedicoUtente;
import org.jboss.tools.example.springmvc.sensitivedata.ContratoMedico;
import org.jboss.tools.example.springmvc.sensitivedata.Instituicao;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.AuthCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;



@Controller
@RequestMapping(value = "/")
@EnableAsync
public class TestingController {
	
	@Autowired
	private PesoDao pesoDao;
	
	@Autowired
	private ContratoMedicoDao cmDao;
	

	@Autowired
	private InstituicaoDao instDao;
	
	@Autowired
	private INRDao inrDao;
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private AlturaDao altDao;
	
	@Autowired
	private ColesterolDao colDao;
	
	@Autowired
	private CirurgiaDao cirurgiaDao;
	
	@Autowired
	private GlicemiaDao glicDao;
	
	@Autowired
	private SaturacaoO2Dao satDao;
	
	@Autowired
	private GuardiaoDao guardiaoDao;
	
	@Autowired
	private TensaoArterialDao tenArtDao;
	
	@Autowired
	private TrigliceridosDao trigDao;
	
	@Autowired
	private MedicoUtenteDao muDao;
	
	@Autowired
	private ConsultaDao consultaDao;
	
	@Autowired
	private ExameDao exameDao;
	
	private List<String> avaliableMeasures;
	
	private AuthController as=new AuthController();
	
	private Storage storage;
	
	private static final int BUFFER_SIZE = 2 * 1024 * 1024;

//	@RequestMapping(value = "/testing", method = RequestMethod.POST, params={"username", "password"})
//	public ModelAndView testeController(HttpServletResponse response, @RequestParam(value = "username") String username,
//											@RequestParam(value = "password") String password) throws NoSuchAlgorithmException, NumberFormatException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
//		
//		ModelAndView mav = new ModelAndView();
//		String hashTest;
//		hashTest = HashTextTest.sha256(password);
//		//Utente ut = utenteDao.novoUtente(username, hashTest);
//		Peso peso = pesoDao.novoPeso(60.4);
//		Altura altura = altDao.novaAltura(195, ut.getNumUtente());
//		Glicemia glicemia = glicDao.novaGlicemia(90, ut.getNumUtente());
//		INR inr = inrDao.novoINR(3, ut.getNumUtente());
//		TensaoArterial tenArt = tenArtDao.novaTensaoArterial(120, 80, ut.getNumUtente());
//		mav.setViewName("testingView");
//		mav.addObject("username", username);
//		mav.addObject("password", ut.getPassword());
//		mav.addObject("peso", peso.toString());
//		mav.addObject("altura", altura.toString());
//		mav.addObject("glicemia", glicemia.toString());
//		mav.addObject("tensaoArt", tenArt.toString());
//		//--------------------------------------------
//		Cookie test = new Cookie("teste", "cookieValue");
//		test.setMaxAge(20);
//		response.addCookie(test);
//		//--------------------------------------------
//		return mav;
//	}
		
	@RequestMapping(value = "/")
	public ModelAndView workaround(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("mainmenu");
		}
		else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/index")
	public ModelAndView vistaController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			mav.addObject("username", session.getAttribute("sessionName"));
			if (session.getAttribute("sessionMode").equals("guardiao")) {
				mav.addObject("sessionMode", "guardian");
			}
			mav.setViewName("mainmenu");
		}
		else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping(value = "/activate")
	public ModelAndView activateController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyRegularLogin(session)){
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("confirmacao_pendente");
		}
		else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/loginUtente", method = RequestMethod.POST, params={"username", "password"})
	@ResponseBody
	public String loginUtente(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpSession session) throws NoSuchAlgorithmException{
		ModelAndView mav = new ModelAndView();
		try{
			System.out.println("ACESSO A BD");
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
			System.out.println("ACESSO FEITO A BD");
			System.out.println(currentUser.getNif() + "CURRENT UTENTE");
			System.out.println(currentUser.getPassword());
			String hashLogin = HashTextTest.sha256(password);
			String loginPassword=currentUser.getPassword();
			String passwordGuardiao = currentUser.getPasswordGuardiao();
			if((hashLogin.equals(loginPassword) && currentUser!=null)){
				session.setAttribute("sessionMode", "user");
				session.setAttribute("sessionID", username);
				session.setAttribute("sessionName", currentUser.getNome());
				mav.addObject("username", currentUser.getNome());
				if (currentUser.isVerified()) {
					return "true";
				}
				else{
					return "activate";
				}
				}
			else if ((hashLogin.equals(passwordGuardiao) && currentUser!=null)) {
				session.setAttribute("sessionMode", "guardiao");
				session.setAttribute("sessionID", username);
				session.setAttribute("sessionName", currentUser.getNome());
				mav.addObject("username", currentUser.getNome());
				if (currentUser.isVerified()) {
					return "true";
				}
				else{
					return "activate";
				}
			}
			else
			{
				return "false";
			}
		}
		catch (Exception e){
			System.out.println("Erro no Login");
			System.out.println(e.getMessage());
			return "false";

		}
		}
	
	
	@RequestMapping(value = "/mudarPassword")
	public String mudarPassword(HttpSession session, @RequestParam(value = "password") String password) throws NoSuchAlgorithmException {
		ModelAndView mav = new ModelAndView();
		String hashLogin = HashTextTest.sha256(password);
		boolean thing = utenteDao.changePassword((Integer) session.getAttribute("sessionID"), hashLogin);
		if (thing) {
			return "true";
		}
		else {
			return "false";
		}
		
	}
	
	

	// preciso de esclarecer umas situacoes
	@RequestMapping(value = "/registo")
	public ModelAndView registoController(HttpSession session){
	ModelAndView mav = new ModelAndView();
	if(!verifyLogin(session)){
		mav.addObject("username", session.getAttribute("sessionName"));
		mav.setViewName("registo");
	}
	else{
		mav.setViewName("index");
	}
	return mav;
}
	
  //Devolve lista de todos os objectos a ser persistidos 
  //Remover da lista Objectos que não são medidas
  //Possivelmente meter noutro pacote para facilitar ou nao
	public List<String> checkMeasures() throws ClassNotFoundException {
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		// add include filters which matches all the classes (or use your own)
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
		List<String> names= new ArrayList<String>();
		// get matching classes defined in the package
		final Set<BeanDefinition> classes = provider.findCandidateComponents("org.jboss.tools.example.springmvc.model");
		for (BeanDefinition bean: classes) {
		    Class<?> clazz = Class.forName(bean.getBeanClassName());
		    names.add(clazz.getName());
		}
	  return names;
	}
	
	//Metodo Experimental para devolver informação/parametros das medições
	@RequestMapping(value = "/medicoes/daoInputs/{object}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Object typeTest(@PathVariable("object") String object)  {
		try {
			List<String> ignoreList = new ArrayList<String>(Arrays.asList(new String[]{"Exame","Boletim", "Cirurgia", "Consulta", "Medicacao", "Medicamento", "Receita", "Vacina","Medicamentoid","EstadoRenovacao"}));
			String[] temp=object.split("\\.");
			String finalname=temp[temp.length-1];
			if(!ignoreList.contains(finalname))
			{
				Class c= Class.forName("org.jboss.tools.example.springmvc.model."+object);
				return c.newInstance();
			}
			else{return null;}
			
		} catch (Exception e) {
			return null;
		}
	  
	}
	 //Transforma lista de objectos anterior num objecto json apresentavel
	@RequestMapping(value="/medicoes/avaliableMeasures",method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> listAllMeasures() throws ClassNotFoundException {
		List<String> finalnames = new ArrayList<String>();
		List<String> ignoreList = new ArrayList<String>(Arrays.asList(new String[]{"Exame","Boletim", "Cirurgia", "Consulta", "Medicacao", "Medicamento", "Receita", "Vacina"}));
		for(String s : checkMeasures())
		{
			String[] temp=s.split("\\.");
			String finalname=temp[temp.length-1];
			if(!ignoreList.contains(finalname)){
				finalnames.add(finalname);
			}
		}
			
		return finalnames; 
    }
	
	
	
	
	@RequestMapping(value = "/registoUtente", method = RequestMethod.POST, params={"nome","num_utente","cc", "morada", "mail", "pass", "passConfirm","telemovel", "emergencia", "nif", "localidade"})
	@ResponseBody
	public List<String> registoUtente(@RequestParam(value = "nome") String username, @RequestParam(value="num_utente") String numUtente,
										@RequestParam(value = "cc") String cc, @RequestParam(value="morada") String morada,
											@RequestParam(value="mail") String mail, @RequestParam(value = "pass") String password,@RequestParam(value = "passConfirm") String passwordConfirm,
												@RequestParam(value = "telemovel") String telemovel, @RequestParam(value = "emergencia") String emergencia, @RequestParam(value="nif") String nif, @RequestParam(value="localidade") String localidade,
													HttpSession session) throws NoSuchAlgorithmException, NumberFormatException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//verificacao de parametros
		boolean resp = true;
		String campo = null;
		String msg=null;
		List<String> finalmsg= new ArrayList<String>();
		int centroId = 0;
		try{
			//------------passwords-------------
			campo = "pass";
			if(!password.equals(passwordConfirm)){
				throw new BadRegistException("Por favor confirme a sua password", campo);
			}
			
			//----------------nome--------------
			resp = resp && (username.length() > 2);
			campo = "nome";
			if(!resp){
				throw new BadRegistException("Nome demasiado pequeno", campo);
			}
			//--------------numUtente----------------
			resp = resp && (numUtente.length() == 9);
			campo = "num_utente";
			if(!resp){
				throw new BadRegistException("Formato de número de Utente incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
			}
			//--------------mail----------------
			campo = "mail";
			if(mail.length() <= 0){
				throw new BadRegistException("Por favor insira um email", campo);
			}
			if(!as.verifyEmail(mail)){
				throw new BadRegistException("Por favor insira um email válido", campo);
			}
			//-------------cc--------------------
			resp = resp && (cc.length() == 8);
			campo = "cc";
			if(!resp){
				throw new BadRegistException("Formato de número de cartão de Cidadao incorrecto, deverá ter 8 digitos (Ex: 12345678)", campo);
			}
			//------------telemovel-----------------
			if(telemovel.length() > 0){
				resp = resp && (telemovel.length() == 9);
				campo = "telemovel";
				if(!resp){
					throw new BadRegistException("Formato de número de telemóvel incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
				}
			}
			else{
				telemovel = "0000";
			}
			//------------emergencia-----------------
			if(emergencia.length() > 0){
				resp = resp && (emergencia.length() == 9);
				campo = "emergencia";
				if(!resp){
					throw new BadRegistException("Formato número de telemóvel de emergência incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
				}
			}
			else{
				emergencia = "0000";
			}
			//-----------nif------------------
			resp = resp && (nif.length() == 9);
			campo = "nif";
			if(!resp){
				throw new BadRegistException("Formato de Nif incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
			}
			//------------localidade------------
			campo = "localidade";
			List<Instituicao> listaInst = instDao.findByLocalidade(localidade);
			if(listaInst.size() == 0){
				throw new BadRegistException("Por favor insira uma Localidade válida", campo);
			}
			else{
				if(listaInst.size() > 1){
					Random ran = new Random();
					int x = ran.nextInt(listaInst.size());
					centroId = listaInst.get(x).getId();
					System.out.println(centroId);
				}
				else{
					centroId = listaInst.get(0).getId();
				}
			}
			//----------SUPER IMPORTANTE--------
			
			
			//-------------verificar numeros---------------
			try{
				campo = "num_utente";
				Integer.parseInt(numUtente);
				campo = "cc";
				Integer.parseInt(cc);
				if(telemovel.length() > 0){
					campo = "telemovel";
					Integer.parseInt(telemovel);
				}
				if(emergencia.length() > 0){
					campo = "emergencia";
					Integer.parseInt(emergencia);
				}
				campo = "nif";
				Integer.parseInt(nif);
			}
			catch(Exception e){
				throw new BadRegistException("Por favor insira um numero", campo);
			}
			if(utenteDao.findUtenteById(Integer.parseInt(numUtente)) != null){
				throw new BadRegistException("Este número já está registado", campo);
			};
			//----------------------------------
		}
		catch(Exception e){
			resp = false;
			finalmsg.add(e.getMessage());
			finalmsg.add(campo);
			System.out.println("ERRO no registo->ver mensagem");
		}

		if(resp)
			try{
				
				String hashTest;
				hashTest = HashTextTest.sha256(password);
				String code = as.nextSessionId();
				System.out.println("Vou mandar o mail no Testing");
				Future future = as.sendEmail(mail,code);
				System.out.println("Ja saltei para o resto");
				//String code = "";
				String codeSms="I<3MEMES";
				try{
				//codeSms = as.sendSms(telemovel);
				}
				catch(Exception e)
				{
					System.out.println("erro no codigo");
				}
				
				int medico;
				List<ContratoMedico> cmNow = cmDao.findByCentro(centroId);
				if(cmNow.size() == 0){
					medico = 0;
				}
				else{
					Random ran = new Random();
					int x = ran.nextInt(cmNow.size());
					medico = cmNow.get(x).getMedicoId();
					}
				System.out.println("vou criar o utente na DB");


				Utente ut = utenteDao.newUtente(username, numUtente, cc, morada, mail, hashTest, telemovel, nif, code, codeSms, emergencia, centroId, medico);
				future.get();
				finalmsg.add("true");
				return finalmsg;
			}
			catch(Exception e){
				finalmsg.add("UNKNOWN");
				System.out.println(e);
				System.out.println(e.getMessage());
			}
		return finalmsg;
		}
	
	@RequestMapping(value = "/registarUtente", method = RequestMethod.POST, params={"nome","num_utente","cc", "morada", "mail", "pass", "passConfirm","telemovel", "emergencia", "nif", "localidade"})
	@ResponseBody
	public List<String> registarUtente(@RequestParam(value = "nome") String username, @RequestParam(value="num_utente") String numUtente,
										@RequestParam(value = "cc") String cc, @RequestParam(value="morada") String morada,
											@RequestParam(value="mail") String mail, @RequestParam(value = "pass") String password,@RequestParam(value = "passConfirm") String passwordConfirm,
												@RequestParam(value = "telemovel") String telemovel, @RequestParam(value = "emergencia") String emergencia, @RequestParam(value="nif") String nif, @RequestParam(value="localidade") String localidade,
													HttpSession session) throws NoSuchAlgorithmException, NumberFormatException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//verificacao de parametros
		boolean resp = true;
		String campo = null;
		String msg=null;
		List<String> finalmsg= new ArrayList<String>();
		int centroId = 0;
		try{
			//------------passwords-------------
			campo = "pass";
			if(!password.equals(passwordConfirm)){
				throw new BadRegistException("Por favor confirme a sua password", campo);
			}
			
			//----------------nome--------------
			resp = resp && (username.length() > 2);
			campo = "nome";
			if(!resp){
				throw new BadRegistException("Nome demasiado pequeno", campo);
			}
			//--------------numUtente----------------
			resp = resp && (numUtente.length() == 9);
			campo = "num_utente";
			if(!resp){
				throw new BadRegistException("Formato de número de Utente incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
			}
			//--------------mail----------------
			campo = "mail";
			if(mail.length() <= 0){
				throw new BadRegistException("Por favor insira um email", campo);
			}
			if(!as.verifyEmail(mail)){
				throw new BadRegistException("Por favor insira um email válido", campo);
			}
			//-------------cc--------------------
			resp = resp && (cc.length() == 8);
			campo = "cc";
			if(!resp){
				throw new BadRegistException("Formato de número de cartão de Cidadao incorrecto, deverá ter 8 digitos (Ex: 12345678)", campo);
			}
			//------------telemovel-----------------
			if(telemovel.length() > 0){
				resp = resp && (telemovel.length() == 9);
				campo = "telemovel";
				if(!resp){
					throw new BadRegistException("Formato de número de telemóvel incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
				}
			}
			else{
				telemovel = "0000";
			}
			//------------emergencia-----------------
			if(emergencia.length() > 0){
				resp = resp && (emergencia.length() == 9);
				campo = "emergencia";
				if(!resp){
					throw new BadRegistException("Formato número de telemóvel de emergência incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
				}
			}
			else{
				emergencia = "0000";
			}
			//-----------nif------------------
			resp = resp && (nif.length() == 9);
			campo = "nif";
			if(!resp){
				throw new BadRegistException("Formato de Nif incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
			}
			//------------localidade------------
			campo = "localidade";
			List<Instituicao> listaInst = instDao.findByLocalidade(localidade);
			if(listaInst.size() == 0){
				throw new BadRegistException("Por favor insira uma Localidade válida", campo);
			}
			else{
				if(listaInst.size() > 1){
					Random ran = new Random();
					int x = ran.nextInt(listaInst.size());
					centroId = listaInst.get(x).getId();
					System.out.println(centroId);
				}
				else{
					centroId = listaInst.get(0).getId();
				}
			}
			//----------SUPER IMPORTANTE--------
			
			
			//-------------verificar numeros---------------
			try{
				campo = "num_utente";
				Integer.parseInt(numUtente);
				campo = "cc";
				Integer.parseInt(cc);
				if(telemovel.length() > 0){
					campo = "telemovel";
					Integer.parseInt(telemovel);
				}
				if(emergencia.length() > 0){
					campo = "emergencia";
					Integer.parseInt(emergencia);
				}
				campo = "nif";
				Integer.parseInt(nif);
			}
			catch(Exception e){
				throw new BadRegistException("Por favor insira um numero", campo);
			}
			if(utenteDao.findUtenteById(Integer.parseInt(numUtente)) != null){
				throw new BadRegistException("Este número já está registado", campo);
			};
			//----------------------------------
		}
		catch(Exception e){
			resp = false;
			finalmsg.add(e.getMessage());
			finalmsg.add(campo);
			System.out.println("ERRO no registo->ver mensagem");
		}

		if(resp)
			try{
				
				String hashTest;
				hashTest = HashTextTest.sha256(password);
				String code = as.nextSessionId();
				//Future future = as.sendEmail(mail,code);
				System.out.println(code);
				//String code = "";
				String codeSms="I<3MEMES";
				try{
				//codeSms = as.sendSms(telemovel);
				}
				catch(Exception e)
				{
					System.out.println("erro no codigo");
				}
				
				int medico;
				List<ContratoMedico> cmNow = cmDao.findByCentro(centroId);
				if(cmNow.size() == 0){
					medico = 0;
				}
				else{
					Random ran = new Random();
					int x = ran.nextInt(cmNow.size());
					medico = cmNow.get(x).getMedicoId();
					}
				
				Utente ut = utenteDao.newUtente(username, numUtente, cc, morada, mail, hashTest, telemovel, nif, code, codeSms, emergencia, centroId, medico);
				muDao.novo(medico, numUtente);
				//future.get();
				finalmsg.add("true");
				return finalmsg;
			}
			catch(Exception e){
				finalmsg.add("UNKNOWN");
				System.out.println(e);
				System.out.println(e.getMessage());
			}
		return finalmsg;
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
	
	public static boolean verifyRegularLogin(HttpSession session){
		if(session.getAttribute("sessionID") == null){
			return false;
		}
		else{
			return true;
		}
	}
	
	@RequestMapping(value="/guardiao") 
	public ModelAndView guardiao(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("atribuirGuardiao");
		return mav;
	}
	
	
	@RequestMapping(value="/atribuirGuardiao")
	public ModelAndView atribuirGuardiao(HttpSession session, @RequestParam(value="numGuardiao") int numGuardiao, @RequestParam(value= "permissao") String permissoes) throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		if (utenteDao.checkId(numGuardiao)) {
			guardiaoDao.atribuirGuardiao( utenteDao.findUtenteById(Integer.parseInt((String) session.getAttribute("sessionID"))), utenteDao.findUtenteById(numGuardiao), permissoes);
		}
		System.out.println("guardiao feito");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mainmenu");
		return mav;
	}
	
	
	@RequestMapping(value = "/isencao")
	public ModelAndView pedirIsencao(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			System.out.println(utenteDao.checkIsencao(Integer.parseInt((String) session.getAttribute("sessionID"))));
			if (!(utenteDao.checkIsencao(Integer.parseInt((String) session.getAttribute("sessionID"))))) {
				mav.addObject("username", session.getAttribute("sessionName"));
				mav.setViewName("isencao_taxas_pedido");
			}
			else {
				mav.setViewName("ja_isento");
			}
		}
		else{
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	@RequestMapping(value= "/verificarIsencao")
	@ResponseBody
	public String verificarIsencao(HttpSession session, @RequestParam(value = "segsoc") int username) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		String userID = (String) session.getAttribute("sessionID");
		Utente u;
		try{
			if (true){
				u = utenteDao.findUtenteByNIF(username);
			}	
		}
		catch(Exception e){
			u = null;
		}
		System.out.println(u);
		if (u != null) {
			utenteDao.mudarIsencao(username);
			return "true";
		}
		else {
			return "false";
		}
		
	}
	
	@RequestMapping(value="/isento")
	public ModelAndView isento(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println("isento");
		mav.setViewName("mainmenu");
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("sessionID");
		mav.setViewName("index");
		return mav;
	}
	
	
	@RequestMapping(value = "/cirurgia")
	public ModelAndView Cirurgia(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			cirurgiaDao.novaCirurgia((String) session.getAttribute("sessionID"), "Dr. Jorge Jesus", "Cirurgia ao Figado");
			System.out.println("cirurgia: " + session.getAttribute("sessionID"));
			List<Cirurgia> lista = cirurgiaDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			session.setAttribute("lista", lista);
			mav.addObject("lista", lista);
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("cirurgia");
		}
		else {
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/confirmarCirurgia")
	public boolean confirmarCirurgia(HttpSession session, @RequestParam(value = "id") Object id){
		return cirurgiaDao.confirmarCirurgia(Integer.parseInt((String) id));
	}
	
	
	@RequestMapping(value = "/medicoes")
	public ModelAndView medicoesController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			mav.addObject("username", session.getAttribute("sessionName"));
			mav.setViewName("medicoes");
		}
		else{
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	//Controlador para guardar as medidas
	@RequestMapping(value = "/medicoes/guardar", method = RequestMethod.POST)
	@ResponseBody
	public String guardarMedicao(@RequestBody HashMap medicoes, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		System.out.println("Medicao a guardar: " + medicoes + "ID da pessoa:" + session.getAttribute("sessionID"));
		switch ((String)medicoes.get("medida")) {
		case "Altura" :
			altDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "Glicemia" :
			glicDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "Colesterol":
			colDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "INR":
			inrDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "Peso":
			pesoDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "SaturacaoO2":
			satDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "Trigliceridos":
			trigDao.novo(Double.parseDouble((String) medicoes.get("valor")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		case "TensaoArterial":
			tenArtDao.novo(Integer.parseInt((String)medicoes.get("max")),Integer.parseInt((String)medicoes.get("min")), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		}
		return "Adicionado";
	}
	
	@RequestMapping(value="/visualizar/{tipoMedida}", method = RequestMethod.GET)
	public ModelAndView showMedidas(HttpSession session,@PathVariable("tipoMedida") String tipoMedida)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("medida", tipoMedida);
		mav.setViewName("graficos");
		return mav;
	}
	
	
	// TESTE DE SESSOES
	//--------------------------------------------------
	@RequestMapping(value = "/sessionSet")
	public ModelAndView setSessao(HttpSession session){
		session.setAttribute("logged", "LOGED USER");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("setSession");
		mav.addObject("loggedUser", session.getAttribute("logged"));
		mav.addObject("tempo", session.getMaxInactiveInterval());
		return mav;
	}
	
	@RequestMapping(value = "/sessionTest")
	public ModelAndView testSessao(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testSession");
		mav.addObject("loggedUser", session.getAttribute("logged"));
		return mav;
	}
	//--------------------------------------------------
	
	@RequestMapping(value="/verifyCode", method = RequestMethod.POST, params={"codigo"})
	@ResponseBody
	public String verifyCode(@RequestParam(value="codigo") String codigo, HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println("A IR BUSCAR O SESSIONID:");
		System.out.println("LISTA DE ATRIBUTOS");
		Enumeration<String> o=session.getAttributeNames();
		while (o.hasMoreElements())
		{
			String s=o.nextElement();
			System.out.println("elemento: " +s +" valor: " + session.getAttribute(s));
		}
		System.out.println("RESULTADO" + session.getAttribute("sessionID"));
		boolean ok = utenteDao.verifyUser((String) session.getAttribute("sessionID"), codigo);
		if (ok) {
			return "true";
			//return "redirect:/index";
		}
		//return "redirect:/";
		return "false";
	}
	
		
	@RequestMapping(value="/obterMedida/{tipoMedida}",method = RequestMethod.GET)
	@ResponseBody
	public List<?> obterMedida(HttpSession session, @PathVariable("tipoMedida") String tipoMedida) {
		List<?> lista = null;
		try {
		System.out.println(tipoMedida);
		System.out.println(session.getAttribute("sessionID"));
		switch (tipoMedida) {
		case "Altura":
			lista = altDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "Glicemia":
			lista = glicDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "Colesterol":
			lista = colDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "INR":
			lista = inrDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "Peso":
			lista = pesoDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "SaturacaoO2":
			lista = satDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "Trigliceridos":
			lista = trigDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		case "TensaoArterial":
			lista = tenArtDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
			break;
		}
		}
		 catch (InvalidKeyException | NumberFormatException | NoSuchAlgorithmException | NoSuchPaddingException
					| IllegalBlockSizeException | BadPaddingException | IOException e) {
				
				e.printStackTrace();
			}
		return lista;
	}
	
	@RequestMapping(value="/obterMedidaMedico/{tipoMedida}/{utente}",method = RequestMethod.GET)
	@ResponseBody
	public List<?> obterMedidaMedico(HttpSession session, @PathVariable("tipoMedida") String tipoMedida,@PathVariable("utente") String utente) {
		List<?> lista = null;
		try {
		switch (tipoMedida) {
		case "Altura":
			lista = altDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "Glicemia":
			lista = glicDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "Colesterol":
			lista = colDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "INR":
			lista = inrDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "Peso":
			lista = pesoDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "SaturacaoO2":
			lista = satDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "Trigliceridos":
			lista = trigDao.findAllByUtente(Integer.parseInt(utente));
			break;
		case "TensaoArterial":
			lista = tenArtDao.findAllByUtente(Integer.parseInt(utente));
			break;
		}
		}
		 catch (InvalidKeyException | NumberFormatException | NoSuchAlgorithmException | NoSuchPaddingException
					| IllegalBlockSizeException | BadPaddingException | IOException e) {
				
				e.printStackTrace();
			}
		return lista;
	}
	 //Controlador Novo de upload
	@RequestMapping(value = "/upload")
	public ModelAndView uploadTemp(HttpSession session) throws FileNotFoundException, IOException, InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		String rootPath = System.getProperty("jboss.server.config.dir"); 
		this.storage=StorageOptions.builder().authCredentials(AuthCredentials.createForJson(new FileInputStream(rootPath+ File.separator+ "bucketkey.json"))).projectId("composite-watch-135111").build().service();
		List<Exame> exames = listBucket(session);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exames", exames);
			mav.setViewName("uploadtest");
		return mav;
	}
	
	//O Puxa carrocas de isto tudo
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file, HttpSession session, @RequestParam("tipo") String tipo) throws InvalidKeyException, NumberFormatException, FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
    	
    	if(name.isEmpty()){name="temporario";}
        if (!file.isEmpty() & !session.getAttribute("sessionID").equals(null) ) {
            try {
                
 
                // Creating the directory to store file
                String rootPath = System.getProperty("jboss.server.config.dir"); 
                System.out.println(rootPath + File.separator + "tmpFiles");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                Bucket bucket = storage.get("userdata-portal-exames");
                String where=bucket.location();
                System.out.println(where);
				System.out.println((String) session.getAttribute("sessionID"));
				String contentType = file.getContentType();
                try (WriteChannel writer = storage.writer(BlobInfo.builder("userdata-portal-exames", (String) session.getAttribute("sessionID") + "/" + file.getOriginalFilename() ).contentType(contentType).build())) {
                    byte[] buffer = new byte[1024];
                    try (InputStream input = file.getInputStream()) {
                      int limit;
                      while ((limit = input.read(buffer)) >= 0) {
                        try {
                          writer.write(ByteBuffer.wrap(buffer, 0, limit));
                        } catch (Exception ex) {
                        	System.out.println("foi aqui: " + buffer + " " + limit);
                          ex.printStackTrace();
                        }
                      }
                    }
                  };
                  exameDao.novoExame(Integer.parseInt((String) session.getAttribute("sessionID")), new Date(), (String) session.getAttribute("sessionID") + "/" + file.getOriginalFilename(), tipo);
            } catch (Exception e) {
            	System.out.println(e.toString());
            	ModelAndView mav = new ModelAndView();
            	mav.setViewName("erro");
                return mav;
            }
        } else {
        	ModelAndView mav = new ModelAndView();
        	mav.setViewName("erro");
            return mav;
        }
        String rootPath = System.getProperty("jboss.server.config.dir"); 
		this.storage=StorageOptions.builder().authCredentials(AuthCredentials.createForJson(new FileInputStream(rootPath+ File.separator+ "bucketkey.json"))).projectId("composite-watch-135111").build().service();
		List<Exame> exames = listBucket(session);
        ModelAndView mav = new ModelAndView();
		mav.addObject("exames", exames);
			mav.setViewName("uploadtest");
		return mav;
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.POST)
    public @ResponseBody
   Object downloadFileHandler(@RequestParam("name") String name) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    	HttpHeaders respHeaders = new HttpHeaders();
    	System.out.println("nome= " + name);
        //Tipo de Return:ResponseEntity<InputStreamResource>
    	Blob blob = storage.get(BlobId.of("userdata-portal-exames", name));
    	PrintStream writeTo = System.out;
    	File temp=File.createTempFile("tempfile", ".temp");
    	writeTo = new PrintStream(new FileOutputStream(temp));
    	if(blob==null){return "Ficheiro não existe"; }
        if (blob.size() < 1_000_000) {
            // Blob is small read all its content in one request
            byte[] content = blob.content();
            writeTo.write(content);
          } else {
            // When Blob size is big or unknown use the blob's channel reader.
            try (ReadChannel reader = blob.reader()) {
              WritableByteChannel channel = Channels.newChannel(writeTo);
              ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
              while (reader.read(bytes) > 0) {
                bytes.flip();
                channel.write(bytes);
                bytes.clear();
              }
            }
          }
        writeTo.close();
        respHeaders.setContentDispositionFormData("attachment", blob.name());
        respHeaders.setContentLength(temp.length());
    	InputStreamResource isr = new InputStreamResource(new FileInputStream(temp));
    	return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
    }

    @RequestMapping(value="/ListFiles")
	@ResponseBody
	public List<Exame> listBucket(HttpSession session) throws InvalidKeyException, NumberFormatException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
    {
    	Bucket bucket = storage.get("userdata-portal-exames");
    	ArrayList<String> filespresent= new ArrayList<String>();
        Iterator<Blob> blobIterator = bucket.list().iterateAll();
        while (blobIterator.hasNext()) {
          filespresent.add(blobIterator.next().name());
        }
        List<Exame> exames = exameDao.findAllByUtente(Integer.parseInt((String) session.getAttribute("sessionID")));
        return exames;
    }
    
    @RequestMapping(value="/ListFilesViaNutente", method = RequestMethod.POST)
	@ResponseBody
	public List<?> listBucketViaNIF(@RequestParam("nutente") int numUtente) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
    {
    	return exameDao.findAllByUtente(numUtente);
    }
       

}
    
   
	

