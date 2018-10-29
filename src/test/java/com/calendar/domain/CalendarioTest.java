package com.calendar.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RunWith(JUnit4.class)
public class CalendarioTest {

	@Test
	public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_maior_que_1970() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JANUARY, 2050);
	}

	@Test
	public void testCriarCalendario_mes_igual_a_dezembro_e_ano_valido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.DECEMBER, 1971);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_maior_que_dezembro_ano_invalido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(12, 1970);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_invalido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JULY, 1969);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_menor_que_janeiro_ano_valido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(-1, 2018);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_entre_janeiro_dezembro_ano_negativo() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JUNE, -2000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_negativo_ano_valido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(-150, 2000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_maior_dezembro_ano_valido() {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(200, 2100);
	}

	@Test
	public void testGetEventosByDia_dia_valido_mes_valido_ano_valido_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JULY, 2018);

		Date data = null;
		String dataString = "01/01/1970";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_dia_valido_mes_valido_ano_valido_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JANUARY, 2000);

		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_inexistente_em_fevereiro() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.NOVEMBER, 2019);

		Date data = null;
		String dataString = "30/02/2018";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_inexistente_em_meses_trinta_dias() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2010);

		Date data = null;
		String dataString = "31/06/2018";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2010);

		Date data = null;
		String dataString = "00/12/2018";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2010);

		Date data = null;
		String dataString = "32/12/2018";
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		data = dateFormatter.parse(dataString);

		calendario.getEventosByDia(data);
	}
}
