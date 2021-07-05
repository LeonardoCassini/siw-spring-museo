package it.uniroma3.siw.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.repository.ArtistaRepository;
import it.uniroma3.siw.spring.repository.CollezioneRepository;
import it.uniroma3.siw.spring.repository.CredenzialiRepository;
import it.uniroma3.siw.spring.repository.CuratoreRepository;
import it.uniroma3.siw.spring.repository.OperaRepository;
import it.uniroma3.siw.spring.service.CredenzialiService;

@Component
public class InitMethod implements CommandLineRunner
{
	@Autowired
	private ArtistaRepository ar;
	@Autowired
	private CollezioneRepository cr;
	@Autowired
	private CuratoreRepository cur;
	@Autowired 
	private OperaRepository or;
	@Autowired
	private CredenzialiRepository credenzialiRepository;
	@Autowired
	private CredenzialiService cs;
	Collezione collezione;
	Artista artista;
	
	@Override
  public void run(String... args) throws Exception {
      cs.saveCredenziali(new Credenziali("franco", "franco"));
  }
}
