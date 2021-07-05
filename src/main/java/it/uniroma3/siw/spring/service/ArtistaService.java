package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService 
{
	@Autowired
	protected ArtistaRepository artistaRepository;
	
	@Transactional
	public Artista saveArtista (Artista artista)
	{
		return this.artistaRepository.save(artista);
	}
	
	@Transactional
	public Artista getArtista(Long id)
	{
		Optional<Artista> result= this.artistaRepository.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public List<Artista> getAllArtista()
	{
		List<Artista> result = new ArrayList<Artista>();
		Iterable <Artista> it = this.artistaRepository.findAll();
		for(Artista artista : it)
		{
			result.add(artista);
		}
		return result;
	}
	
	public boolean duplicato(Artista artista)
	{
		List<Artista> result=this.artistaRepository.esiste(artista.getBiografia(),artista.getCognome(),artista.getDataMorte(),artista.getDataNascita(),artista.getLuogoMorte(),artista.getLuogoNascita(),artista.getNazionalita(),artista.getNome());
		if (result.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void cancella(Long id)
	{
		this.artistaRepository.deleteById(id);
	}
}
