package it.uniroma3.siw.spring.model;

//direttive
import javax.persistence.*;

@Entity
public class Opera 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String titolo;
	private String annoRealizzazione;
	private String descrizione;
	private String immagine;
	@ManyToOne
	private Collezione collezione;
	@ManyToOne
	private Artista artista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAnnoRealizzazione() {
		return annoRealizzazione;
	}
	public void setAnnoRealizzazione(String annoRealizzazione) {
		this.annoRealizzazione = annoRealizzazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Collezione getCollezione() {
		return collezione;
	}
	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
}
