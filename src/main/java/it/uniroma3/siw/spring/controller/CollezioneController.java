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
import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController 
{
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private CuratoreService curatoreService;
	@Autowired
	private CollezioneValidator collezioneValidator;
	@Autowired
	private OperaService operaService;

	@RequestMapping("/collezioni")
	public String collezioni() 
	{
		return "collezioni";
	}

	@RequestMapping("/collezione")
	public String collezione() 
	{
		return "collezione";
	}

	@RequestMapping(value="/collezioni",method= RequestMethod.GET)
	public String showCollezioni(Model model)
	{
		model.addAttribute("collezioni",this.collezioneService.getAllCollezioni());
		return"collezioni.html";
	}

	@RequestMapping(value="/collezioni/{id}",method= RequestMethod.GET)
	public String showCollezione(@PathVariable("id") Long id,Model model)
	{
		model.addAttribute("collezione",this.collezioneService.getCollezione(id));
		return"collezione.html";
	}

	@RequestMapping(value="/inserisciCollezione",method = RequestMethod.GET)
	public String addCollezione(Model model)
	{
		model.addAttribute("collezione", new Collezione());
		model.addAttribute("curatore", this.curatoreService.getAllCuratore());
		return"/inserisciCollezione";
	}
	
	@RequestMapping(value="/inserisciCollezione", method=RequestMethod.POST)
	public String aggiungi(@ModelAttribute("collezione") Collezione collezione, Model model,BindingResult br)
	{
		this.collezioneValidator.validate(collezione, br);
		if(!br.hasErrors())
		{
			if(collezione.getCuratore().getMatricola()==0)
			{
				collezione.setCuratore(null);
				this.collezioneService.saveCollezione(collezione);
			}
			else
			{
				this.collezioneService.saveCollezione(collezione);
			}
			return "redirect:/inserisciCollezione";
		}
		return "/default";
	}
	
	@RequestMapping(value="/modificaCollezione",method= RequestMethod.GET)
	public String inizioModCollezione(Model model)
	{
		model.addAttribute("collezioni", this.collezioneService.getAllCollezioni());
		return"/modificaCollezione";
	}
	
	@RequestMapping(value="/updCollezione/{id}",method=RequestMethod.GET)
	public String formOpera(@PathVariable("id")Long id, Model model)
	{
		model.addAttribute("collezione",this.collezioneService.getCollezione(id));
		model.addAttribute("curatore", this.curatoreService.getAllCuratore());
		return"/updCollezione";
	}
	
	@RequestMapping(value="/updCollezione/{id}", method=RequestMethod.POST)
	public String aggiorna(@ModelAttribute("collezione") Collezione collezione, Model model,BindingResult br)
	{
		this.collezioneValidator.validate(collezione, br);
		if(!br.hasErrors())
		{
			if(collezione.getCuratore().getMatricola()==0)
			{
				collezione.setCuratore(null);
				this.collezioneService.saveCollezione(collezione);
			}
			else
			{
				this.collezioneService.saveCollezione(collezione);
			}
			return "redirect:/modificaCollezione";
		}
		return "redirect:/default";
	}
	
	@RequestMapping(value="/cancCollezione/{id}",method=RequestMethod.POST)
	public String cancellaCollezione(@PathVariable("id")Long id)
	{
		List<Opera> opere=this.operaService.getOpereByCollezione(id);
		this.operaService.setNullCollezione(opere);
		this.collezioneService.cancella(id);
		return"redirect:/modificaCollezione";
	}
}
