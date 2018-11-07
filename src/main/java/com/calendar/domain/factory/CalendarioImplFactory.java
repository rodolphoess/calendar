package com.calendar.domain.factory;

import com.calendar.domain.Calendario;
import com.calendar.domain.CalendarioImpl;

public class CalendarioImplFactory implements CalendarioFactory {

	@Override
	public Calendario criaCalendarioMensal(int mes, int ano) {
		return CalendarioImpl.criaCalendarioMensal(mes, ano);
	}

}
