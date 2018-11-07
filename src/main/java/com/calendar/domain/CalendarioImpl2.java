package com.calendar.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
		if (mes > Calendar.DECEMBER) {
			throw new IllegalArgumentException("O mes informado é maior do que Dezembro.");
		}

		if (mes < Calendar.JANUARY) {
			throw new IllegalArgumentException("O mês informado é menor do que Janeiro.");
		}
	}

	public static void validaAno(int ano) {
		if (ano < 1970) {
			throw new IllegalArgumentException("O ano informado é menor do que 1970.");
		}
	}

	@Override
	public void adicionarEvento(String titulo, Date dataEvento, String link) {
		Evento evento = Evento.criarEvento(titulo, dataEvento, link);

		eventos.add(evento);
	}

	@Override
	public List<Evento> getEventosByDia(Date diaEvento) {
		List<Evento> eventosDoDia = new ArrayList<Evento>();

		for (Evento evento : eventos) {
			if (diaEvento == eventos.iterator().next().getData()) {
				eventosDoDia.add(evento);
			}
		}

		return eventosDoDia;
	}

	public void instanciaCalendar(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
	}

	@Override
	public Set<Evento> getEventos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJson() throws JsonException {
		// TODO Auto-generated method stub
		return null;
	}

}
