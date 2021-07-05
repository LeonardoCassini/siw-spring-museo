package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;
@Service
public class OperaService 
{
	@Autowired
	protected OperaRepository operaRepository;
	
	public Opera getOpera(Long id)
	{
		Optional<Opera> result=this.operaRepository.findById(id);
		return result.orElse(null);
	}
	
	public List<Opera> getAllOpere()
	{
		List<Opera> result = new ArrayList<Opera>();
		Iterable<Opera> it = this.operaRepository.findAll();
		for(Opera opera :it)
		{
			result.add(opera);
		}
		return result;
	}
	
	public List<Opera> getOpereAnno(String anno)
	{
		List<Opera> result = new ArrayList<Opera>();
		Iterable<Opera> it = this.operaRepository.findByAnnoRealizzazione(anno);
		for(Opera opera :it)
		{
			result.add(opera);
		}
		return result;
	}
	
	public List<Integer> getAnnate()
	{
		List<Integer> result = new ArrayList<Integer>();
		Iterable<Integer> it = this.operaRepository.findByAnni();
		for(Integer integer :it)
		{
			result.add(integer);
		}
		return result;
	}
	
	public boolean duplicato(Opera opera)
	{
		List<Opera> result=this.operaRepository.esiste(opera.getTitolo(), opera.getAnnoRealizzazione(),opera.getDescrizione(), opera.getImmagine(),opera.getArtista().getId(), opera.getCollezione().getId());
		if (result.size() > 0)
			return true;
		else 
			return false;
	}
	
	public List<Opera> getOpereByCollezione(Long idCollezione)
	{
		List<Opera> result = new ArrayList<Opera>();
		Iterable<Opera> it = this.operaRepository.findByIdCollezione(idCollezione);
		for(Opera opera :it)
		{
			result.add(opera);
		}
		return result;
	}
	
	@Transactional
	public void setNullCollezione(List<Opera> opere)
	{
		Iterator<Opera> it=opere.iterator();
		while(it.hasNext())
		{
			Opera opera=it.next();
			opera.setCollezione(null);
		}
	}
	
	public List<Opera> getOpereByArtista(Long idArtista)
	{
		List<Opera> result = new ArrayList<Opera>();
		Iterable<Opera> it = this.operaRepository.findByIdArtista(idArtista);
		for(Opera opera :it)
		{
			result.add(opera);
		}
		return result;
	}
	
	@Transactional
	public void setNullArtista(List<Opera> opere)
	{
		Iterator<Opera> it=opere.iterator();
		while(it.hasNext())
		{
			Opera opera=it.next();
			opera.setArtista(null);
		}
	}
	
	@Transactional
	public Opera saveOpera(Opera opera)
	{
		return this.operaRepository.save(opera);
	}
	
	@Transactional
	public void cancella(Long id)
	{
		this.operaRepository.deleteById(id);
	}
}
