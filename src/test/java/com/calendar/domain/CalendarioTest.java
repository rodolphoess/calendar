package com.calendar.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

@RunWith(JUnit4.class)
public class CalendarioTest {

    @Test
    public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_maior_que_1970(){
        Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JANUARY,2050);
    }

    @Test
    public void testCriarCalendario_mes_igual_a_dezembro_e_ano_valido(){
        Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.DECEMBER,1971);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCriarCalendario_mes_maior_que_dezembro_ano_invalido(){
        Calendario calendario = CalendarioImpl.criaCalendarioMensal(12,1970);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCriarCalendario_mes_entre_janeiro_e_dezembro_ano_invalido(){
        Calendario calendario = CalendarioImpl.criaCalendarioMensal(Calendar.JULY, 1969);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCriarCalendario_mes_menor_que_janeiro_ano_valido(){
        Calendario calendario = CalendarioImpl.criaCalendarioMensal(-1,2018);
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
}
