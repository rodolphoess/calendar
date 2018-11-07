package com.calendar.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.calendar.utils.JsonException;

public interface Calendario {

	/**
	 * Cria um evento com o título e data passados por parâmetro.
	 *
	 * @param titulo
	 * @param dataEvento
	 */
	void adicionarEvento(String titulo, Date dataEvento, String link);

	/**
	 * Método que retorna os eventos de um determinado dia.
	 *
	 * @param diaEvento
	 * @return
	 */
	List<Evento> getEventosByDia(Date diaEvento);

	Set<Evento> getEventos();

	int getMes();

	int getAno();

	String getJson() throws JsonException;

}