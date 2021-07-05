package it.uniroma3.siw.spring.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;
import java.util.List;


@Entity
public class Curatore 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long matricola;
	private  String nome;
	private  String cognome;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascita;
	private String luogoNascita;
	private String telefono;
	private String email;
	@OneToMany(mappedBy="curatore")
	private List<Collezione> collezioni;
	
	//getter
	
	//matricola
	public Long getMatricola() 
	{
		return matricola;
	}
	//nome
	public String getNome() 
	{
		return nome;
	}
	//cognome
	public String getCognome() 
	{
		return cognome;
	}
	//data di nascita
	public LocalDate getDataNascita() 
	{
		return dataNascita;
	}
	//luogo di nacita
	public String getLuogoNascita() 
	{
		return luogoNascita;
	}
	//telefono
	public String getTelefono() 
	{
		return telefono;
	}
	//email
	public String getEmail() 
	{
		return email;
	}
	//collezioni
	public List<Collezione> getCollezioni() 
	{
		return collezioni;
	}
	
	//setter
	
	//matricola
	public void setMatricola(Long matricola) 
	{
		this.matricola = matricola;
	}
	//nome
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	//cognome
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	//data di nascita
	public void setDataNascita(LocalDate dataNascita) 
	{
		this.dataNascita = dataNascita;
	}
	//luogo di nascita
	public void setLuogoNascita(String luogoNascita) 
	{
		this.luogoNascita = luogoNascita;
	}
	//telefono
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	//email
	public void setEmail(String email) 
	{
		this.email = email;
	}
	//collezioni
	public void setCollezioni(List<Collezione> collezioni) 
	{
		this.collezioni = collezioni;
	}
}
