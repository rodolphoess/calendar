package com.calendar.domain;

import com.calendar.utils.CalendarUtils;
import com.calendar.utils.JSONProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Classe que representa um calendário.
 * 
 * @author Daniel Smith
 *
 */
public class Calendario{

	/**
	 * Mês visualizado no calendário.
	 */
	private int mes;
	
	/**
	 * Ano da visualização do calendário.
	 */
	private int ano;
	
	/**
	 * Eventos visualizados no calendário.
	 */
    private Set<Evento> eventos;

    private Calendario(int mes, int ano){    
        this.eventos = new HashSet<Evento>();
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Cria um calendário mensal para o mês informado.
     * 
     * @param mes
     * @param ano
     * @return
     */
    public static Calendario criaCalendarioMensal(int mes, int ano){
        return new Calendario(mes,ano);
    }

    /**
     * Cria um evento com o título e data passados por parâmetro.
     * 
     * @param titulo
     * @param dataEvento
     */
    public void adicionarEvento(String titulo, Date dataEvento, String link) {
    	adicionarEvento(Evento.criarEvento(titulo, dataEvento, link));
    }
    
    private void adicionarEvento(Evento evento){
        
        if(evento == null){
            throw new IllegalArgumentException("O evento não pode ser nulo");
        }

        eventos.add(evento);

    }   
    
    /**
     * Retorna os eventos do período informado.
     * 
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<Evento> getEventosByPeriodo(Date dataInicio, Date dataFim){
    	List<Evento> eventosPeriodo = new ArrayList<Evento>();
    	
    	for(Evento evento : eventos) {
    		if(CalendarUtils.isIntervalosDeDatasConflitantes(evento.getData(), evento.getData(), dataInicio, dataFim)) {
    			eventosPeriodo.add(evento);
    		}
    	}
    	
    	return eventosPeriodo;
    }
    
    /**
     * Método que retorna os eventos de um determinado dia.
     * 
     * @param diaEvento
     * @return
     */
    public List<Evento> getEventosByDia(Date diaEvento){
    	List<Evento> eventosDia = new ArrayList<Evento>();
    	
    	for(Evento evento : eventos) {
    		if(CalendarUtils.descartarHoras(evento.getData()).compareTo(CalendarUtils.descartarHoras(diaEvento)) == 0) {
        		eventosDia.add(evento);
        	}
    	}    	
    	
    	return eventosDia;
    }

    public Set<Evento> getEventos(){
        return eventos;
    }

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}
	
	public String getJson(){
		return JSONProcessor.toJSON(this);
	}
	
}