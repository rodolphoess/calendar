package com.calendar.domain;

import java.util.Date;

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
        return new Evento(titulo,data,link);
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
