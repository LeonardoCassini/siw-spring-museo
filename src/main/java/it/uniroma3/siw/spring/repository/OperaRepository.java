package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long> 
{
//	//utilizzata per trovare i duplicati
//	public List<Opera> findByTitolo(String titolo);
	
	@Query(value="select * from opera where titolo=?1 and anno_realizzazione=?2 and descrizione=?3 and immagine=?4 and artista_id=?5 and collezione_id=?6", nativeQuery=true)
	public List<Opera> esiste(String titolo, String anno,String descrizione,String immagine,Long idArtista,Long idCollezione);
	
	//permette di trovare tutte gli anni delle varie opere senza duplicati
	@Query(value="select distinct anno_realizzazione from opera", nativeQuery=true)
	public List<Integer> findByAnni();
	
	//permette di trovare tutte le opere dato uno specifico anno
	public List<Opera> findByAnnoRealizzazione(String anno);
	
	@Query(value="select * from opera where collezione_id=?1", nativeQuery=true)
	public List<Opera> findByIdCollezione(Long id);
	
	@Query(value="select * from opera where artista_id=?1", nativeQuery=true)
	public List<Opera> findByIdArtista(Long id);	
}