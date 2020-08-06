package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sessao {
	@Id
	private Integer id;
	private LocalTime horario;
	private Sala sala;
	private Filme filme;	
	
	public Sessao() {
		
	}
	public Sessao( LocalTime horario, Sala sala, Filme filme) {
		super();
		this.horario = horario;
		this.sala = sala;
		this.filme = filme;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}

}

