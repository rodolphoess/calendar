package com.calendar.domain;

import com.calendar.utils.CalendarUtils;
import com.calendar.utils.JSONProcessor;

import java.util.*;


/**
 * Classe que representa um calendário.
 *
 * @author Daniel Smith
 */
public class CalendarioImpl implements Calendario {

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

    private CalendarioImpl(int mes, int ano) {
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
    public static Calendario criaCalendarioMensal(int mes, int ano) {
        validarMes(mes);
        validarAno(ano);

        return new CalendarioImpl(mes, ano);
    }

    /**
     * Método para realizar a validação do mês antes de criar o calendário.
     *
     * @param mes
     */
    public static void validarMes(int mes) {
        if (mes > Calendar.DECEMBER) {
            throw new IllegalArgumentException("O mês deve ser menor que DEZEMBRO (11 na api calendar).");
        }

        if (mes < Calendar.JANUARY) {
            throw new IllegalArgumentException("O mês deve ser maior que JANEIRO (0 na api calendar");
        }
    }

    /**
     * Método para validar o ano antes de criar o calendário.
     *
     * @param ano
     */
    public static void validarAno(int ano) {
        if (ano < 1970) {
            throw new IllegalArgumentException("O ano deve ser maior que 1970");
        }
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#adicionarEvento(java.lang.String, java.util.Date, java.lang.String)
	 */
    @Override
	public void adicionarEvento(String titulo, Date dataEvento, String link) {

        if(!CalendarUtils.dataPertenceAoMes(dataEvento, this.mes,this.ano)){
            throw new IllegalArgumentException("A data informada não pertence ao mês do calendário ");
        }

        Evento evento = Evento.criarEvento(titulo, dataEvento, link);

        adicionarEvento(evento);
    }

    private void adicionarEvento(Evento evento) {

        if (evento == null) {
            throw new IllegalArgumentException("O evento não pode ser nulo");
        }

        eventos.add(evento);

    }
    public void  criarCalendarioMensal (int mes, int ano) {
    	if (ano<0) {
    		throw new IllegalArgumentException("O ano não pode ser negatívo");
    	}
    	if (ano<1970) {
    		throw new IllegalArgumentException("O calendario começa em 1970");
    	}
    	if (mes>12) {
    		throw new IllegalArgumentException("O mês informado não existe");
    	}
    	if (mes<0) {
    		throw new IllegalArgumentException("Meses negativos não existem");
    	}
    	
    }
    
    
    /**
     * Retorna os eventos do período informado.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<Evento> getEventosByPeriodo(Date dataInicio, Date dataFim) {
        List<Evento> eventosPeriodo = new ArrayList<Evento>();

        for (Evento evento : eventos) {
            if (CalendarUtils.isIntervalosDeDatasConflitantes(evento.getData(), evento.getData(), dataInicio, dataFim)) {
                eventosPeriodo.add(evento);
            }
        }

        return eventosPeriodo;
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#getEventosByDia(java.util.Date)
	 */
    @Override
	public List<Evento> getEventosByDia(Date diaEvento) {
        List<Evento> eventosDia = new ArrayList<Evento>();

        for (Evento evento : eventos) {
            if (CalendarUtils.descartarHoras(evento.getData()).compareTo(CalendarUtils.descartarHoras(diaEvento)) == 0) {
                eventosDia.add(evento);
            }
        }

        return eventosDia;
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#getEventos()
	 */
    @Override
	public Set<Evento> getEventos() {
        return eventos;
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#getMes()
	 */
    @Override
	public int getMes() {
        return mes;
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#getAno()
	 */
    @Override
	public int getAno() {
        return ano;
    }

    /* (non-Javadoc)
	 * @see com.calendar.domain.ICalendar#getJson()
	 */
    @Override
	public String getJson() {
        return JSONProcessor.toJSON(this);
    }

}