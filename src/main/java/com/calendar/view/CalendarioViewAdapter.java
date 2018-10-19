package com.calendar.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.calendar.domain.Calendario;

import com.calendar.domain.Evento;
import com.calendar.utils.CalendarUtils;

/**
* Classe que implementa um adapter para visualização de um calendario.
* 
* @see com.calendar.domain.Calendario
* 
* @author Daniel Smith
*/
public class CalendarioViewAdapter{
	
    /**
     * Atributo que guarda as semanas que estão sendo exibidas no calendário.
     */
    private List<Semana> semanas;
    
    /**
     * Mês da visualização do calendário.
     */
    private String mes;
    
    /**
     * Ano da visualização do calendário.
     */
    private int ano;
    
    /**
     * Cria o adapter de visualização do calendário.
     * 
     * @param calendario
     * @return
     */
    public static CalendarioViewAdapter criarViewAdapter(Calendario calendario) {
    	  if(calendario == null){
              throw new IllegalArgumentException("Não é possível instanciar a visualização do calendário sem passar um calendário válido");
          }
    	
    	return new CalendarioViewAdapter(calendario);
    }
    
    /**
     * Construtor do adapter.
     * 
     * @throws IllegalArgumentException caso o calend�rio seja inv�lido.
     * 
     * @param calendario
     */
    private CalendarioViewAdapter(Calendario calendario){      
    	final Date primeiroDiaMes = CalendarUtils.createDate(1, calendario.getMes(), calendario.getAno());
        final Date ultimoDiaMes = CalendarUtils.getMaximoDia(calendario.getMes()+1, calendario.getAno());
        this.semanas = montarSemanas(primeiroDiaMes,ultimoDiaMes, calendario);
        this.mes = CalendarUtils.getNomeMes(calendario.getMes() + 1);
        this.ano = calendario.getAno();     
    }

    /**
     * Monta as semanas para exibi��o do calend�rio.
     * 
     * @param diaInicial
     * @param diaFinal
     * @param calendario
     * @return
     */
	private List<Semana> montarSemanas(Date diaInicial, Date diaFinal, Calendario calendario){
		
		List<Semana> semanas = new ArrayList<Semana>();
		
		int quantidadeSemanas = getQuantidadeSemanasPeriodo(diaInicial, diaFinal);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(diaInicial);
		
		while (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY) {
		    calendar.add(Calendar.DATE, -1); 
		}
		
		for(int i = 1; i <= quantidadeSemanas ; i++) {
			
			Semana semana = new Semana();
		
			for(int diaDaSemana = Calendar.SUNDAY; diaDaSemana <= Calendar.SATURDAY; diaDaSemana++) {
				final Date data = calendar.getTime();
				
				final Dia dia = new Dia(data, toEventoViewAdapter(calendario.getEventosByDia(data)));
				// Variável auxiliar para conseguir o dia antecessor ao dia inicial
				Calendar aux = Calendar.getInstance();
				aux.setTime(diaInicial);
				aux.add(Calendar.DATE, -1);
				if(CalendarUtils.descartarHoras(data).after(aux.getTime()) && CalendarUtils.descartarHoras(data).before(diaFinal)) {
					dia.marcarComoVisualizavel();
				}
				
				semana.addDia(dia);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			semanas.add(semana);		
			
		}
		
		return semanas;
	}

	/**
	 * Retorna a quantidade de semanas compreendidas dentro do período.
	 * 
	 * @param diaInicial
	 * @param diaFinal
	 * @return
	 */
	private int getQuantidadeSemanasPeriodo(Date diaInicial, Date diaFinal) {
		if(diaInicial == null || diaFinal == null) {
			throw new IllegalArgumentException("As datas devem formar um intervalo fechado.");
		}
		
		int resultado = CalendarUtils.getNumeroSemana(diaFinal) - CalendarUtils.getNumeroSemana(diaInicial) + 1;
		
		if (resultado < 1) {
			return resultado + 52; // pra quando o dia inicial e o dia final forem de anos diferentes (em outras palavras, pra quando for selecionado dezembro)
		}
		
		return resultado;
		
	}

	/**
	 * Converte uma coleção de eventos em EventoViewAdapter
	 * 
	 * @param eventos
	 * @return
	 */
    public List<EventoViewAdapter> toEventoViewAdapter(List<Evento> eventos){
    	List<EventoViewAdapter> eventosViewAdapter = new ArrayList<EventoViewAdapter>();
    	
        for(Evento evento : eventos){
        	eventosViewAdapter.add(EventoViewAdapter.criaEventoViewAdapter(evento));
        }
        
        return eventosViewAdapter;
    }

	public List<Semana> getSemanas() {
		return semanas;
	}
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

}