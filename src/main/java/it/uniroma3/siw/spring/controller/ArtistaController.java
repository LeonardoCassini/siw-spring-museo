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
import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class ArtistaController 
{
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private ArtistaValidator artistaValitor;
	@Autowired
	private OperaService operaService;
	
	@RequestMapping("/artisti")
	public String artisti() 
	{
	    return "artisti";
	}
	
	@RequestMapping("/artista")
	public String artista() 
	{
	    return "artista";
	}
	
	@RequestMapping(value="/artisti",method= RequestMethod.GET)
	 public String showArtisti(Model model)
	 {
		 model.addAttribute("artisti",this.artistaService.getAllArtista());
		 return"artisti.html";
	 }
	 
	 @RequestMapping(value="/artista/{id}",method= RequestMethod.GET)
	 public String showArtista(@PathVariable("id") Long id,Model model)
	 {
		 model.addAttribute("artista",this.artistaService.getArtista(id));
		 return"artista.html";
	 }
	 
	 @RequestMapping(value = "/inserisciArtista", method = RequestMethod.GET)
	    public String addArtista(Model model) 
		{
	        model.addAttribute("artista", new Artista());
	        return "/inserisciArtista";
	    }
		
		@RequestMapping(value="/inserisciArtista", method=RequestMethod.POST)
		public String aggiungi(@ModelAttribute("artista") Artista artista, Model model,BindingResult br)
		{
			this.artistaValitor.validate(artista, br);
			if(!br.hasErrors())
			{
				this.artistaService.saveArtista(artista);
				return "redirect:/inserisciArtista";
			}
			return "redirect:/default";
		}
		
		@RequestMapping(value="/modificaArtista",method=RequestMethod.GET)
		public String inizioModArtista(Model model)
		{
			model.addAttribute("artisti",this.artistaService.getAllArtista());
			return"/modificaArtista";
		}
		
		@RequestMapping(value="/updArtista/{id}", method=RequestMethod.GET)
		public String formArtista(@PathVariable("id")Long id, Model model)
		{
			model.addAttribute("artista", this.artistaService.getArtista(id));
			return"/updArtista";
		}
		
		@RequestMapping(value="/updArtista/{id}", method=RequestMethod.POST)
		public String aggiorna(@ModelAttribute("artista") Artista artista, Model model,BindingResult br)
		{
			this.artistaValitor.validate(artista, br);
			if(!br.hasErrors())
			{
				this.artistaService.saveArtista(artista);
				return "redirect:/modificaArtista";
			}
			return "redirect:/default";
		}
		
		@RequestMapping(value="/cancArtista/{id}",method=RequestMethod.POST)
		public String cancellaCollezione(@PathVariable("id")Long id)
		{
			List<Opera> opere=this.operaService.getOpereByArtista(id);
			this.operaService.setNullArtista(opere);
			this.artistaService.cancella(id);
			return"redirect:/modificaArtista";
		}
}
