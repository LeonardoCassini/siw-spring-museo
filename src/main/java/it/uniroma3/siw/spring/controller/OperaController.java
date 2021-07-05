package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController 
{
	@Autowired
	private OperaService operaService;
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private OperaValidator operaValidator;
	
	
	@RequestMapping("/opera")
	public String opera() 
	{
	    return "opera";
	}
	
	@RequestMapping("/anni")
	public String anni()
	{
		return"anni";
	}
	
	@RequestMapping("/opereAnno")
	public String opereAnno()
	{
		return "opereAnno";
	}
	
	@RequestMapping("/delOperaGood")
	public String cancRiuscita(Model model)
	{
		model.addAttribute("opere", this.operaService.getAllOpere());
		return "/delOperaGood";
	}
	
	@RequestMapping(value="/opera/{id}",method= RequestMethod.GET)
	 public String showOpera(@PathVariable("id") Long id,Model model)
	 {
		 model.addAttribute("opera",this.operaService.getOpera(id));
		 return"opera.html";
	 }
	
	@RequestMapping(value="/anni", method= RequestMethod.GET)
	public String MostraAnni(Model model)
	{
		model.addAttribute("anni",this.operaService.getAnnate());
		return"anni.html";
	}
	
	@RequestMapping(value="/anno/{String}", method=RequestMethod.GET)
	public String showOpereAnno(@PathVariable("String") String anno, Model model)
	{
		model.addAttribute("opereAnno", this.operaService.getOpereAnno(anno));
		return "opereAnno.html";
	}
	
	@RequestMapping(value = "/inserisciOpera", method = RequestMethod.GET)
    public String addOpera(Model model) 
	{
        model.addAttribute("opera", new Opera());
        model.addAttribute("artista", this.artistaService.getAllArtista());
        model.addAttribute("collezione", this.collezioneService.getAllCollezioni());
        return "/inserisciOpera";
    }
	
	@RequestMapping(value="/inserisciOpera", method=RequestMethod.POST)
	public String aggiungi(@ModelAttribute("opera") Opera opera, Model model,BindingResult br)
	{
		this.operaValidator.validate(opera, br);
		if(!br.hasErrors())
		{
			if(opera.getCollezione().getId()==0)
			{
				opera.setCollezione(null);
				this.operaService.saveOpera(opera);
			}
			else
			{
				this.operaService.saveOpera(opera);
			}
			return "redirect:/inserisciOpera";
		}
		return "redirect:/default";
	}
	
	@RequestMapping(value="/modificaOpera",method= RequestMethod.GET)
	public String inizioModOpera(Model model)
	{
		model.addAttribute("opere", this.operaService.getAllOpere());
		return"/modificaOpera";
	}
	
	@RequestMapping(value="/cancOpera/{id}",method=RequestMethod.POST)
	public String cancellaOpera(@PathVariable("id")Long id)
	{
		this.operaService.cancella(id);
		return"redirect:/modificaOpera";
	}
	
	@RequestMapping(value="/updOpera/{id}",method=RequestMethod.GET)
	public String formOpera(@PathVariable("id")Long id, Model model)
	{
		model.addAttribute("opera",this.operaService.getOpera(id));
		model.addAttribute("artista", this.artistaService.getAllArtista());
        model.addAttribute("collezione", this.collezioneService.getAllCollezioni());
		return"/updOpera";
	}
	
	@RequestMapping(value="/updOpera/{id}", method=RequestMethod.POST)
	public String aggiorna(@ModelAttribute("opera") Opera opera, Model model,BindingResult br)
	{
		this.operaValidator.validate(opera, br);
		if(!br.hasErrors())
		{
			if(opera.getCollezione().getId()==0)
			{
				opera.setCollezione(null);
				this.operaService.saveOpera(opera);
			}
			else
			{
				this.operaService.saveOpera(opera);
			}
			return "redirect:/modificaOpera";
		}
		return "redirect:/default";
	}
	
}
