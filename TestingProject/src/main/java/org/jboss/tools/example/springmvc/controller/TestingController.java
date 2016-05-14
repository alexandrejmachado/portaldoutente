package org.jboss.tools.example.springmvc.controller;

import org.jboss.tools.example.springmvc.data.AlturaDao;
import org.jboss.tools.example.springmvc.data.ColesterolDao;
import org.jboss.tools.example.springmvc.data.ConsultaDao;
import org.jboss.tools.example.springmvc.data.GlicemiaDao;
import org.jboss.tools.example.springmvc.data.INRDao;
import org.jboss.tools.example.springmvc.data.PesoDao;
import org.jboss.tools.example.springmvc.data.SaturacaoO2Dao;
import org.jboss.tools.example.springmvc.data.TensaoArterialDao;
import org.jboss.tools.example.springmvc.data.TrigliceridosDao;
import org.jboss.tools.example.springmvc.data.UtenteDao;
import org.jboss.tools.example.springmvc.model.Altura;
import org.jboss.tools.example.springmvc.model.Glicemia;
import org.jboss.tools.example.springmvc.model.INR;
import org.jboss.tools.example.springmvc.model.Peso;
import org.jboss.tools.example.springmvc.model.SaturacaoO2;
import org.jboss.tools.example.springmvc.model.TensaoArterial;
import org.jboss.tools.example.springmvc.sensitivedata.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.SendFailedException;
import javax.servlet.http.Cookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jboss.tools.example.springmvc.controller.HashTextTest;

@Controller
@RequestMapping(value = "/")
public class TestingController {
	
	@Autowired
	private PesoDao pesoDao;
	
	@Autowired
	private INRDao inrDao;
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private AlturaDao altDao;
	
	@Autowired
	private ColesterolDao colDao;
	
	@Autowired
	private GlicemiaDao glicDao;
	
	@Autowired
	private SaturacaoO2Dao satDao;
	
	@Autowired
	private TensaoArterialDao tenArtDao;
	
	@Autowired
	private TrigliceridosDao trigDao;
	
	@Autowired
	private ConsultaDao consultaDao;
	
	private List<String> avaliableMeasures;
	
	private AuthController as=new AuthController();
	
	
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
	public ModelAndView workaround(){
		ModelAndView mav = new ModelAndView();
			mav.setViewName("index");
		return mav;
	}
	
	
	@RequestMapping(value = "/index")
	public ModelAndView vistaController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
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
		if(verifyLogin(session)){
			mav.setViewName("confirmacao_pendente");
		}
		else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping(value = "/calendario")
	public ModelAndView calendarioController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
			mav.setViewName("calendario");
		}
		else{
			mav.setViewName("redirect:/index");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/loginUtente", method = RequestMethod.POST, params={"username", "password"})
	@ResponseBody
	public String loginUtente(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpSession session) throws NoSuchAlgorithmException{
		ModelAndView mav = new ModelAndView();
		try{
			Utente currentUser = utenteDao.findUtenteById(Integer.parseInt(username));
			String hashLogin = HashTextTest.sha256(password);
			String loginPassword=currentUser.getPassword();
			if(hashLogin.equals(loginPassword) && currentUser!=null){
				session.setAttribute("sessionID", username);
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
			return "false";

		}
		}

	// preciso de esclarecer umas situacoes
	@RequestMapping(value = "/registo")
	public ModelAndView registoController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registo");
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
		HashMap<String, Object> inputfields;
		inputfields = new HashMap<String,Object>();
		inputfields.put("Glicemia",new Glicemia());
		System.out.println("object = " + object);
		try {
			Class c= Class.forName("org.jboss.tools.example.springmvc.model."+object);
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	  
	}
	 //Transforma lista de objectos anterior num objecto json apresentavel
	@RequestMapping(value="/medicoes/avaliableMeasures",method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> listAllMeasures() throws ClassNotFoundException {
		List<String> finalnames = new ArrayList<String>();
		for(String s : checkMeasures())
		{
			String[] temp=s.split("\\.");
			finalnames.add(temp[temp.length-1]);
		}
			
		return finalnames; 
    }
	
	
	
	
	@RequestMapping(value = "/registoUtente", method = RequestMethod.POST, params={"nome","num_utente","cc", "morada", "mail", "pass", "telemovel", "emergencia"})
	@ResponseBody
	public List<String> registoUtente(@RequestParam(value = "nome") String username, @RequestParam(value="num_utente") String numUtente,
										@RequestParam(value = "cc") String cc, @RequestParam(value="morada") String morada,
											@RequestParam(value="mail") String mail, @RequestParam(value = "pass") String password,
												@RequestParam(value = "telemovel") String telemovel, @RequestParam(value = "emergencia") String emergencia, @RequestParam(value="nif") String nif,HttpSession session) throws NoSuchAlgorithmException, NumberFormatException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		//verificacao de parametros
		boolean resp = true;
		String campo = null;
		String msg=null;
		List<String> finalmsg= new ArrayList<String>();
		try{
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
			//------------emergencia-----------------
			if(emergencia.length() > 0){
				resp = resp && (emergencia.length() == 9);
				campo = "emergencia";
				if(!resp){
					throw new BadRegistException("Formato número de telemóvel de emergência incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
				}
			}
			//-----------nif------------------
			resp = resp && (nif.length() == 9);
			campo = "nif";
			if(!resp){
				throw new BadRegistException("Formato de Nif incorrecto, deverá ter 9 digitos (Ex: 123456789)", campo);
			}
			//-------------verificar numeros---------------
			try{
				campo = "num_utente";
				Integer.parseInt(numUtente);
				System.out.print("numero: ");
				campo = "cc";
				Integer.parseInt(cc);
				campo = "telemovel";
				Integer.parseInt(telemovel);
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
			System.out.println(e.getMessage());
		}

		if(resp)
			try{
				
				String hashTest;
				hashTest = HashTextTest.sha256(password);
				String code = as.sendEmail(mail);
				String codeSms="";
				try{
				//codeSms = as.sendSms(telemovel);
				}
				catch(Exception e)
				{
					System.out.println("erro no codigo");
				}
				Utente ut = utenteDao.newUtente(username, numUtente, cc, morada, mail, hashTest, telemovel, nif, code, codeSms);
				finalmsg.add("true");
				return finalmsg;
			}
			catch(Exception e){
				finalmsg.add("UNKNOWN");
				System.out.println(e.getMessage());
			}
		return finalmsg;
		}
	
	
	private boolean verifyLogin(HttpSession session){
		if(session.getAttribute("sessionID") == null){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	@RequestMapping(value = "/isencao")
	public ModelAndView pedirIsencao(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("isencao_taxas_pedido");
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
	
	
	@RequestMapping(value = "/medicoes")
	public ModelAndView medicoesController(HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(verifyLogin(session)){
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
		System.out.println(medicoes);
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
			tenArtDao.novo(Integer.parseInt((String)medicoes.get("max")),(Integer)medicoes.get("min"), Integer.parseInt((String)session.getAttribute("sessionID")));
			break;
		}
		return "Adicionado";
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
		boolean ok = utenteDao.verifyUser((String) session.getAttribute("sessionID"), codigo);
		if (ok) {
			return "true";
			//return "redirect:/index";
		}
		//return "redirect:/";
		return "false";
	}
	
	
	
	@RequestMapping(value="/testing")
	@ResponseBody
	public List<?> testing(HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		try {
			consultaDao.novo(0, 123123123, 2, "Sala A4", new Date());
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
			
		}
		return consultaDao.findAllByUtente(123123123);
	}
	
	@RequestMapping(value="/obterMedida")
	@ResponseBody
	public List<?> obterMedida(HttpSession session, String tipoMedida) {
		List<?> lista = null;
		try {
		System.out.println(tipoMedida);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	}
	
}
