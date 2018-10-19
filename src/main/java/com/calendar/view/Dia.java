package com.calendar.view;

import com.calendar.utils.CalendarUtils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Classe que representa um dia na visualizaçãoo do calendário.
 *
 * @author Daniel Smith
 *
 */
public final class Dia {

    /**
     * Dia da semana associado ao dia.
     *
     *  @see Calendar#DAY_OF_WEEK
     */
    private Integer diaDaSemana;

    /**
     * Dia do mês associado ao dia.
     */
    private Integer diaDoMes;

    /**
     * Mês associado ao dia.
     */
    private Integer mes;

    /**
     * Retorna se o dia deve ser exibido na visualização do calendário.
     * Útil na exibiçãoo de semanas que estrapolem o limite do mês a ser exibido em um calendário mensal.
     */
    private boolean visualizavel;

    /**
     * Coleção dos eventos que irão ocorrer no dia.
     */
    private Collection<EventoViewAdapter> eventos;

    /**
     * Construtor para o dia.
     *
     * @param dia
     * @param eventos
     */
    Dia(Date dia, Collection<EventoViewAdapter> eventos){
        if(dia == null) {
            throw new IllegalArgumentException("Não é possével criar um dia sem passar uma data.");
        }

        this.diaDaSemana = CalendarUtils.getDiaSemanaByData(dia);
        this.diaDoMes = CalendarUtils.getDiaByData(dia);
        this.mes = CalendarUtils.getMesByData(dia);
        this.eventos = eventos;
    }

    /**
     * Marca um dia como visualizável.
     */
    public void marcarComoVisualizavel() {
        visualizavel = true;
    }

    /**
     * Retorna se o dia é um dia do final de semana.
     *
     * @return
     */
    public boolean isFinalDeSemana(){
        return diaDaSemana == Calendar.SATURDAY || diaDaSemana == Calendar.SUNDAY;
    }

    public boolean isVisualizavel() {
        return this.visualizavel;
    }

    public Integer getDiaDaSemana() {
        return diaDaSemana;
    }

    public Integer getDiaDoMes() {
        return diaDoMes;
    }

    public Integer getMes() {
        return mes;
    }

    public Collection<EventoViewAdapter> getEventos() {
        return eventos;
    }

}
