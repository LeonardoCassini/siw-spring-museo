package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService
{
	@Autowired
	protected CuratoreRepository curatoreRepository;
	
	@Transactional
	public Curatore getCuratore(Long matricola)
	{
		Optional<Curatore> result= this.curatoreRepository.findById(matricola);
		return result.orElse(null);
	}
	
	@Transactional
	public Curatore saveCuratore(Curatore curatore) 
	{
		return this.curatoreRepository.save(curatore);
	}
	
	public List<Curatore> getAllCuratore()
	{
		List<Curatore> result = new ArrayList<Curatore>();
		Iterable<Curatore> it = this.curatoreRepository.findAll();
		for(Curatore curatore :it)
		{
			result.add(curatore);
		}
		return result;
	}
	
	@Transactional
	public void cancella(Long id)
	{
		this.curatoreRepository.deleteById(id);
	}
}
