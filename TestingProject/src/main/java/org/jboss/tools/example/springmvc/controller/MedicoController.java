package org.jboss.tools.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {

	@RequestMapping(value="/")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cenas");
		return mav;
	}
}
