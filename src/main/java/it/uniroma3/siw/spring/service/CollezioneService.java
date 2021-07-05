package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService 
{
	@Autowired
	protected CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione getCollezione(Long id)
	{
		Optional<Collezione> result = this.collezioneRepository.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public List<Collezione> getAllCollezioni()
	{
		List<Collezione> result = new ArrayList<Collezione>();
		Iterable<Collezione> it = this.collezioneRepository.findAll();
		for(Collezione collezione:it)
		{
			result.add(collezione);
		}
		return result;
	}
	
	@Transactional
	public Collezione saveCollezione(Collezione collezione)
	{
		return this.collezioneRepository.save(collezione);
	}
	
	public boolean duplicato(Collezione collezione)
	{
		List<Collezione> result=this.collezioneRepository.esiste(collezione.getNome(),collezione.getDescrizione(),collezione.getCuratore().getMatricola());
		if (result.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void cancella(Long id)
	{
		this.collezioneRepository.deleteById(id);
	}
	
	public List<Collezione> getCollezioniByCuratore(Long matrCuratore)
	{
		List<Collezione> result = new ArrayList<Collezione>();
		Iterable<Collezione> it = this.collezioneRepository.findByMatricolaCuratore(matrCuratore);
		for(Collezione collezione :it)
		{
			result.add(collezione);
		}
		return result;
	}
	
	@Transactional
	public void setNullCuratore(List<Collezione> collezioni)
	{
		Iterator<Collezione> it=collezioni.iterator();
		while(it.hasNext())
		{
			Collezione collezione=it.next();
			collezione.setCuratore(null);
		}
	}
}
