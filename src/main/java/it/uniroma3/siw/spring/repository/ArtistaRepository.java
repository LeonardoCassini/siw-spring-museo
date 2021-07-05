package it.uniroma3.siw.spring.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista,Long>
{
	@Query(value="select * from artista where biografia=?1 and cognome=?2 and data_morte=?3 and data_nascita=?4 and luogo_morte=?5 and luogo_nascita=?6 and nazionalita=?7 and nome=?8", nativeQuery=true)
	public List<Artista> esiste(String biografia,String cognome, LocalDate dataMorte,LocalDate dataNascita,String luogoMorte,String luogoNascita,String nazionalita, String nome);
}
