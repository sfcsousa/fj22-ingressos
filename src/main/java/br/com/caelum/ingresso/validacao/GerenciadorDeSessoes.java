package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoes {
	private List<Sessao> sessoesDaSala;
	
	
	public GerenciadorDeSessoes(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
		
	}
	
	public boolean cabe(Sessao sessaoNova) {
		if(terminaAmanha(sessaoNova))
			return false;
		
				
				return sessoesDaSala.stream().noneMatch(sessaoExistente -> 
						horarioIsConflitante(sessaoExistente,sessaoNova)
						); //horarios conflitantes
	}
	
	public boolean terminaAmanha(Sessao sessao) {
		LocalDateTime terminoSesssaoNova = getTerminoSessaoComDiaHoje(sessao);
		LocalDateTime ultimoSegundoHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		return terminoSesssaoNova.isAfter(ultimoSegundoHoje);
		
	}
	private  boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		LocalDateTime inicioSessaoExistente = getInicioSesssaoComDiaHoje(sessaoExistente);
		LocalDateTime terminoSessaoExistente = getTerminoSessaoComDiaHoje(sessaoExistente);
		LocalDateTime inicioSessaoNova = getInicioSesssaoComDiaHoje(sessaoNova);
		LocalDateTime terminoSessaoNova = getTerminoSessaoComDiaHoje(sessaoNova);
	
		boolean sessaoNovaTerminaAntesDaExistente = terminoSessaoNova.isBefore(inicioSessaoExistente);
		boolean sessaoNovaComecaDepoisDaExistente = terminoSessaoExistente.isBefore(inicioSessaoNova);
		if (sessaoNovaTerminaAntesDaExistente || sessaoNovaComecaDepoisDaExistente) {
			return false;
		}
		return true;
		
	}
	
	private LocalDateTime getTerminoSessaoComDiaHoje(Sessao sessao) {
		LocalDateTime inicioSessaoNova = getInicioSesssaoComDiaHoje(sessao);
		return inicioSessaoNova.plus(sessao.getFilme().getDuracao());
	}
	private LocalDateTime getInicioSesssaoComDiaHoje(Sessao sessao) {
		return sessao.getHorario().atDate(LocalDate.now());
	}
}
