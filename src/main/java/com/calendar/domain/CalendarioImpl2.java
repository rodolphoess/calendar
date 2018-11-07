package com.calendar.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.calendar.utils.JsonException;

public class CalendarioImpl2 implements Calendario {

	@Override
	public void adicionarEvento(String titulo, Date dataEvento, String link) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Evento> getEventosByDia(Date diaEvento) {
		// TODO Auto-generated method stub
		return null;
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
