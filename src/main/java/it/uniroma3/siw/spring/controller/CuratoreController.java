package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;

@Controller
public class CuratoreController 
{
	@Autowired
	private CuratoreService curatoreService;
	@Autowired
	private CuratoreValidator curatoreValidator;
	@Autowired
	private CollezioneService collezioneService;
	
	
	@RequestMapping(value="/inserisciCuratore", method=RequestMethod.GET)
    public String addOpera(Model model) 
	{
        model.addAttribute("curatore", new Curatore());
        return "/inserisciCuratore";
    }
	
	@RequestMapping(value="/inserisciCuratore", method=RequestMethod.POST)
	public String aggiungi(@ModelAttribute("curatore") Curatore curatore, Model model,BindingResult br)
	{
		this.curatoreValidator.validate(curatore, br);
		if(!br.hasErrors())
		{
			this.curatoreService.saveCuratore(curatore);
			return "redirect:/inserisciCuratore";
		}
		return "/default";
	}
	
	@RequestMapping(value="/modificaCuratore",method= RequestMethod.GET)
	public String inizioModCuratore(Model model)
	{
		model.addAttribute("curatori", this.curatoreService.getAllCuratore());
		return"/modificaCuratore";
	}
	
	@RequestMapping(value="/updCuratore/{matricola}",method=RequestMethod.GET)
	public String formCuratore(@PathVariable("matricola")Long matricola, Model model)
	{
		model.addAttribute("curatore",this.curatoreService.getCuratore(matricola));
		return"/updCuratore";
	}
	
	@RequestMapping(value="/updCuratore/{matricola}", method=RequestMethod.POST)
	public String aggiorna(@ModelAttribute("curatore") Curatore curatore, Model model,BindingResult br)
	{
		this.curatoreValidator.validate(curatore, br);
		if(!br.hasErrors())
		{
			this.curatoreService.saveCuratore(curatore);
			return "redirect:/modificaCuratore";
		}
		return "redirect:/default";
	}
	
	@RequestMapping(value="/cancCuratore/{matricola}",method=RequestMethod.POST)
	public String cancellaCuratore(@PathVariable("matricola")Long matricola)
	{
		List<Collezione> collezioni=this.collezioneService.getCollezioniByCuratore(matricola);
		this.collezioneService.setNullCuratore(collezioni);
		this.curatoreService.cancella(matricola);
		return"redirect:/modificaCuratore";
	}
}
