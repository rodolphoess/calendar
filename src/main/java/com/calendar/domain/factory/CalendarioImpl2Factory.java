package com.calendar.domain.factory;

import com.calendar.domain.Calendario;
import com.calendar.domain.CalendarioImpl;
import com.calendar.domain.CalendarioImpl2;

public class CalendarioImpl2Factory implements CalendarioFactory {

	@Override
	public Calendario criaCalendarioMensal(int mes, int ano) {
		return CalendarioImpl2.criaCalendarioMensal(mes, ano);
	}

}
