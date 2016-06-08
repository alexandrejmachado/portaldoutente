package org.jboss.tools.example.springmvc.controller;

import org.jboss.tools.example.springmvc.data.MedicamentoDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/medicacao")
public class MedicacaoController {
	
	public MedicamentoDao medDao;

}
