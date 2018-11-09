package com.calendar.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.calendar.utils.CalendarUtils;
import com.calendar.utils.JSONProcessor;
import com.calendar.utils.JsonException;

public class CalendarioImpl2 implements Calendario {
	private int ano;
	private int mes;
	private Collection<Evento> eventos;

	private CalendarioImpl2(int mes, int ano) {
		this.ano = ano;
		this.mes = mes;
		eventos = new ArrayList<Evento>();
	}

	public static Calendario criaCalendarioMensal(int mes, int ano) {
		validaMes(mes);
		validaAno(ano);

		return new CalendarioImpl2(mes, ano);
	}

	public static void validaMes(int mes) {
		if (mes > Calendar.DECEMBER)
			throw new IllegalArgumentException("O mes informado é maior do que Dezembro.");
		
		if (mes < Calendar.JANUARY)
			throw new IllegalArgumentException("O mês informado é menor do que Janeiro.");
	}

	public static void validaAno(int ano) {
		if (ano < 1970) 
			throw new IllegalArgumentException("O ano informado é menor do que 1970.");
	}

	@Override
	public void adicionarEvento(String titulo, Date dataEvento, String link) {
		if (!CalendarUtils.dataPertenceAoMes(dataEvento, mes, ano))
			throw new IllegalArgumentException("A data informada não é válida.");
		
		Evento evento = Evento.criarEvento(titulo, dataEvento, link);
		
		eventos.add(evento);
	}

	@Override
	public List<Evento> getEventosByDia(Date diaEvento) {
		if (!CalendarUtils.dataPertenceAoMes(diaEvento, mes, ano))
			throw new IllegalArgumentException("A data informada não é válida.");
		
		List<Evento> eventosDoDia = new ArrayList<Evento>();

		for (Evento evento : eventos) {
			if (diaEvento == eventos.iterator().next().getData())
				eventosDoDia.add(evento);
		}

		return eventosDoDia;
	}

	@Override
	public Set<Evento> getEventos() {
		Set<Evento> eventosGerais = new HashSet<Evento>();
		
		for (Evento evento : eventos)
			eventosGerais.add(evento);
		
		return eventosGerais;
	}

	@Override
	public int getMes() {
		return mes;
	}

	@Override
	public int getAno() {
		return ano;
	}

	@Override
	public String getJson() throws JsonException {
		return JSONProcessor.toJSON(this);
	}

}
