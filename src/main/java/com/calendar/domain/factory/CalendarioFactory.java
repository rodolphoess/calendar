package com.calendar.domain.factory;

import com.calendar.domain.Calendario;

public interface CalendarioFactory {

	public Calendario criaCalendarioMensal(int mes, int ano);
}
