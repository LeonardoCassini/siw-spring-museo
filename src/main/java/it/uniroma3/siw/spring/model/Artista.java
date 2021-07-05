package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artista 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private  String nome;
	private  String cognome;
	@Column(columnDefinition="TEXT")
	private String biografia;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascita;
	private String luogoNascita;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataMorte;
	@Column(nullable=true)
	private String luogoMorte;
	private String nazionalita;
	@OneToMany(mappedBy="artista")
	private List<Opera> opere;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getLuogoNascita() {
		return luogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	public LocalDate getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}
	public String getLuogoMorte() {
		return luogoMorte;
	}
	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<Opera> getOpere() {
		return opere;
	}
	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
}
