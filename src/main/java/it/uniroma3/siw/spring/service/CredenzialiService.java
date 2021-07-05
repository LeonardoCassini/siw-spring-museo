package it.uniroma3.siw.spring.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.repository.CredenzialiRepository;

@Service
public class CredenzialiService 
{
	@Autowired
	protected CredenzialiRepository credenzialiRepository;
	
	@Autowired
    protected PasswordEncoder passwordEncoder;
	
	@Transactional
	public Credenziali saveCredenziali(Credenziali credenziali)
	{
		credenziali.setRole(Credenziali.ADMIN_ROLE);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepository.save(credenziali);
	}
	
	@Transactional
	public Credenziali getCredenziali(String name)
	{
		Optional<Credenziali> result=this.credenzialiRepository.findByUsername(name);
		return result.orElse(null);
	}
}
