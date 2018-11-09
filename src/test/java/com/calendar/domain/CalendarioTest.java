package com.calendar.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.calendar.domain.factory.CalendarioFactory;
import com.calendar.domain.factory.CalendarioImpl2Factory;
import com.calendar.utils.JsonException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(JUnit4.class)
public class CalendarioTest {

	private CalendarioFactory calendarioFactory;
	private List<Evento> eventosAdicionadosList;
	private Set<Evento> eventosAdicionadosSet;

	@Before
	public void setup() {
		this.calendarioFactory = new CalendarioImpl2Factory();
		this.eventosAdicionadosList = new ArrayList<Evento>();
		this.eventosAdicionadosSet = new HashSet<Evento>();
	}

	@Test
	public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_maior_que_1970() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2050);
		
		assertNotNull(calendario);
	}

	@Test
	public void testCriarCalendario_mes_igual_a_dezembro_e_ano_valido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 1971);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_maior_que_dezembro_ano_invalido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(12, 1970);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_invalido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JULY, 1969);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_menor_que_janeiro_ano_valido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(-1, 2018);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_entre_janeiro_dezembro_ano_negativo() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, -2000);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_negativo_ano_valido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(-150, 2000);
		
		assertNotNull(calendario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarCalendario_mes_maior_dezembro_ano_valido() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(200, 2100);
		
		assertNotNull(calendario);
	}

	@Test
	public void testGetEventosByDia_dia_valido_mes_valido_ano_valido_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 1970);

		Date data = null;
		String dataString = "01/01/1970";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test
	public void testGetEventosByDia_dia_valido_mes_valido_ano_valido_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_dia_inexistente_em_fevereiro() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "30/02/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_dia_inexistente_em_meses_trinta_dias() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 2018);

		Date data = null;
		String dataString = "31/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_dia_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "00/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_dia_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "32/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_mes_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "01/13/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_mes_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);

		Date data = null;
		String dataString = "31/00/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventosByDia_invalido_ano_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 1969);

		Date data = null;
		String dataString = "15/06/1969";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwert", data, "asdfg");

		eventosAdicionadosList = calendario.getEventosByDia(data);

		assertEquals(calendario.getEventosByDia(data), eventosAdicionadosList);
	}

	@Test
	public void testAdicionarEvento_valido_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);

		Date data = null;
		String dataString = "01/01/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("a", data, "a");
	}

	@Test
	public void testAdicionarEvento_valido_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopasdfghjklç", data, "qwertyuiop");
	}

	@Test(expected = NullPointerException.class)
	public void testAdicionarEvento_invalido_titulo_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 2018);

		Date data = null;
		String dataString = "01/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento(null, data, "qwert");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_titulo_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 2018);

		Date data = null;
		String dataString = "30/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopasdfghjklçz", data, "a");
	}

	@Test(expected = NullPointerException.class)
	public void testAdicionarEvento_invalido_link_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.APRIL, 2018);

		Date data = null;
		String dataString = "15/04/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiop", data, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_link_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.OCTOBER, 2020);

		Date data = null;
		String dataString = "09/10/2020";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qw", data, "qwertyuiopa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_dia_fevereiro_invalido() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.FEBRUARY, 2018);

		Date data = null;
		String dataString = "30/02/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyui", data, "qwertyu");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_dia_invalido_mes_trinta_dias() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 2018);

		Date data = null;
		String dataString = "31/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopasdfg", data, "qwer");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_dia_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "00/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopas", data, "as");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_dia_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "32/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwer", data, "qwe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_mes_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "01/13/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopa", data, "qwertyui");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_invalido_mes_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);

		Date data = null;
		String dataString = "31/00/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopasdfghjk", data, "qwert");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarEvento_ano_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 1969);

		Date data = null;
		String dataString = "15/06/1969";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuio", data, "qwertyuio");
	}

	@Test
	public void testBuscarMes_valido_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);
		assertEquals(calendario.getMes(), Calendar.JANUARY);
	}

	@Test
	public void testBuscarMes_valido_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);
		assertEquals(calendario.getMes(), Calendar.DECEMBER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarMes_invalido_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(-10, 2018);
		assertEquals(calendario.getMes(), -10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarMes_invalido_acima_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(20, 2018);
		assertEquals(calendario.getMes(), 20);
	}

	@Test
	public void testBuscarAno_valido_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 1970);
		assertEquals(calendario.getAno(), 1970);
	}

	@Test
	public void testBuscarAno_valido_atual() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);
		assertEquals(calendario.getAno(), 2018);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarAno_invalido_abaixo_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 1969);
		assertEquals(calendario.getAno(), 1969);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarAno_invalido_ano_negativo() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, -2018);
		assertEquals(calendario.getAno(), -2018);
	}
	
	@Test
	public void testGetEventos_valido_limite_inferior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JANUARY, 2018);

		Date data = null;
		String dataString = "01/01/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("a", data, "a");

		eventosAdicionadosSet = calendario.getEventos();

		assertEquals(calendario.getEventos(), eventosAdicionadosSet);
	}

	@Test
	public void testGetEventos_valido_limite_superior() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.DECEMBER, 2018);

		Date data = null;
		String dataString = "31/12/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = dataFormatada.parse(dataString);

		calendario.adicionarEvento("qwertyuiopasdfghjklç", data, "qwertyuiop");

		eventosAdicionadosSet = calendario.getEventos();

		assertEquals(calendario.getEventos(), eventosAdicionadosSet);
	}

	@Test
	public void testGetEventos_valido_mais_de_um_evento() throws ParseException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.JUNE, 2018);

		Date dataEventoUm = null;
		Date dataEventoDois = null;
		String dataStringEventoUm = "01/06/2018";
		String dataStringEventoDois = "30/06/2018";
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		dataEventoUm = dataFormatada.parse(dataStringEventoUm);
		dataEventoDois = dataFormatada.parse(dataStringEventoDois);

		calendario.adicionarEvento("qwertyui", dataEventoUm, "qwerty");
		calendario.adicionarEvento("qwer", dataEventoDois, "qwertyui");

		eventosAdicionadosSet = calendario.getEventos();

		assertEquals(calendario.getEventos(), eventosAdicionadosSet);
	}

	@Test
	public void testGetEventos_valido_nenhum_evento() {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.MAY, 2018);
		
		assertNull(calendario.getEventos());
	}
	
	@Test(expected = StackOverflowError.class)
	public void testGetJson_valido() throws JsonException {
		Calendario calendario = calendarioFactory.criaCalendarioMensal(Calendar.NOVEMBER, 2018);
		
		String JSON = calendario.getJson();
		
		assertEquals(calendario.getJson(), JSON);
	}
}
