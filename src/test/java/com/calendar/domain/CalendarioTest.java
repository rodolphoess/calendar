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
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JULY, 1970);

		Date data = null;
		String dataString = "01/01/1970";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_dia_valido_mes_valido_ano_valido_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JANUARY, 2018);

		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_inexistente_em_fevereiro() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.NOVEMBER, 2018);

		Date data = null;
		String dataString = "30/02/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_inexistente_em_meses_trinta_dias() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "31/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "00/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}

	@Test
	public void testGetEventosByDia_invalido_dia_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "32/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}
	
	@Test
	public void testGetEventosByDia_invalido_mes_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "01/13/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}
	
	@Test
	public void testGetEventosByDia_invalido_mes_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "31/00/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_ano_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 1969);

		Date data = null;
		String dataString = "15/06/1969";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.getEventosByDia(data);
	}
	
	@Test
	public void testAdicionarEvento_valido_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JUNE, 2018);
		
		Date data = null;
		String dataString = "01/01/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("a", data, "a");
	}
	
	@Test
	public void testAdicionarEvento_valido_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.SEPTEMBER, 2018);
		
		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopasdfghjklç", data, "qwertyuiop");
	}
	
	@Test
	public void testAdicionarEvento_invalido_titulo_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.MARCH, 2018);
		
		Date data = null;
		String dataString = "01/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento(null, data, "qwert");
	}
	
	@Test
	public void testAdicionarEvento_invalido_titulo_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);
		
		Date data = null;
		String dataString = "30/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopasdfghjklçz", data, "a");
	}
	
	@Test
	public void testAdicionarEvento_invalido_link_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.MAY, 2018);
		
		Date data = null;
		String dataString = "15/04/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiop", data, null);
	}
	
	@Test
	public void testAdicionarEvento_invalido_link_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.APRIL, 2020);
		
		Date data = null;
		String dataString = "09/10/2020";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qw", data, "qwertyuiopa");
	}
	
	@Test
	public void testAdicionarEvento_invalido_dia_fevereiro_invalido() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.AUGUST, 2018);
		
		Date data = null;
		String dataString = "30/02/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyui", data, "qwertyu");
	}
	
	@Test
	public void testAdicionarEvento_invalido_dia_invalido_mes_trinta_dias() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.OCTOBER, 2018);
		
		Date data = null;
		String dataString = "31/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopasdfg", data, "qwer");
	}
	
	@Test
	public void testAdicionarEvento_invalido_dia_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.NOVEMBER, 2018);
		
		Date data = null;
		String dataString = "00/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopas", data, "as");
	}
	
	@Test
	public void testAdicionarEvento_invalido_dia_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.DECEMBER, 2018);
		
		Date data = null;
		String dataString = "32/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwer", data, "qwe");
	}
	
	@Test
	public void testAdicionarEvento_invalido_mes_acima_limite_superior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JANUARY, 2018);
		
		Date data = null;
		String dataString = "01/13/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopa", data, "qwertyui");
	}
	
	@Test
	public void testAdicionarEvento_invalido_mes_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.FEBRUARY, 2018);
		
		Date data = null;
		String dataString = "31/00/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuiopasdfghjk", data, "qwert");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_ano_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.MARCH, 1969);
		
		Date data = null;
		String dataString = "15/06/1969";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);
		
		calendario.adicionarEvento("qwertyuio", data, "qwertyuio");
	}
}
