package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController 
{
	@RequestMapping(value= {"/","home"}, method= RequestMethod.GET)
	public String login(Model model)
	{
		return "home";
	}
	
	@RequestMapping("/informazioni")
	public String informazioni() 
	{
	    return "informazioni";
	}
}