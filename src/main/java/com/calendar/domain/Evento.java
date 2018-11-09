package com.calendar.domain;

import java.util.Calendar;
import java.util.Date;

import com.calendar.utils.CalendarUtils;

/**
 * Classe que representa um evento de um calendário.
 * 
 * @author Daniel Smith
 *
 */
public class Evento{
	
	/**
	 * Título do evento.
	 */
    private String titulo;
    
    /**
     * Data da ocorrência do evento.
     */
    private Date data;
    
    /**
     * Link do evento.
     * 
     */
    private String link;
    
    private Evento(String titulo, Date data, String link){
        this.titulo = titulo;
        this.data = data;
        this.link = link;
    }

    /**
     * Cria o evento com o título e a data de ocorrência passados por parâmetro..
     * 
     * @param titulo
     * @param data
     *
     * @return
     */
    public static Evento criarEvento(String titulo, Date data, String link){
    	validarTitulo(titulo);
    	validarData(data);
    	validarLink(link);
    	
        return new Evento(titulo,data,link);
    }

	private static void validarLink(String link) {
		if (link == null || link.isEmpty())
			throw new NullPointerException("Link inválido por estar vazio.");
		
		if (link.length() > 10)
			throw new IllegalArgumentException("Link inválido por exceder o tamanho máximo de 10 caracteres.");
	}

	private static void validarData(Date data) {
		Calendar calendar = instanciaCalendar(data);
		
		int dia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		int mes = calendar.get(Calendar.MONTH);
		int ano = calendar.get(Calendar.YEAR);
		
		if (dia < 1)
			throw new IllegalArgumentException("O dia informado é inválido por ser menor do que 01.");
		
		if (mes < Calendar.JANUARY || mes > Calendar.DECEMBER)
			throw new IllegalArgumentException("O mês informado é inválido por ser maior do que 12 ou menor do que 01.");
		
		if (ano < 1970)
			throw new IllegalArgumentException("O ano informado é inválido por ser menor do que 1970.");
	}
	
	public static Calendar instanciaCalendar(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		return calendar;
	}

	private static void validarTitulo(String titulo) {
		if (titulo == null || titulo.isEmpty())
			throw new NullPointerException("Título inválido por estar vazio.");
		
		if (titulo.length() > 20)
			throw new IllegalArgumentException("Título inválido por exceder o tamanho máximo de 20 caracteres.");
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getData() {
		return data;
	}

	public String getLink() {
		return link;
	}

}
