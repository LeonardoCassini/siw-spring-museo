package it.uniroma3.siw.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Collezione 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	@ManyToOne
	private Curatore curatore;
	@OneToMany(mappedBy="collezione")
	private List<Opera> opere;
	
	
	//getter

	//id
	public Long getId() 
	{
		return id;
	}
	//nome
	public String getNome() 
	{
		return nome;
	}
	//descrizione
	public String getDescrizione() 
	{
		return descrizione;
	}
	//curatore
	public Curatore getCuratore() 
	{
		return curatore;
	}
	//opere
	public List<Opera> getOpere()
	{
		return opere;
	}

	//setter
	
	//id
	public void setId(Long id) 
	{
		this.id = id;
	}
	//nome
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	//descrizione
	public void setDescrizione(String descrizione) 
	{
		this.descrizione = descrizione;
	}
	//curatore
	public void setCuratore(Curatore curatore) 
	{
		this.curatore = curatore;
	}
	//opere
	public void setOpere(List<Opera> opere)
	{
		this.opere= opere;
	}
}
