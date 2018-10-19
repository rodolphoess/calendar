package com.calendar.view;

import com.calendar.domain.Evento;

import java.util.Date;

/**
 * View adapter para a visualização de um evento do calendário.
 *
 * @author Daniel Smith
 */
public class EventoViewAdapter {

    /**
     * Título do evento.
     */
    private String titulo;

    /**
     * Data do evento.
     */
    private Date data;

    /**
     * Link da url que o usuário será redirecionado ao clicar no popup para exibir um detalhamento do evento.
     */
    private String link;

    /**
     * Representa se ao clicar no link do evento, será possível exibir um detalhamento do evento;
     */
    private boolean clicavel;

    public EventoViewAdapter(String titulo, Date data, String link) {
        this.titulo = titulo;
        this.data = data;
        this.link = link;

        //Caso o link não seja vazio, deverá ser possível navegar por meio do link.
        if (link != null && !link.isEmpty()) {
            this.clicavel = true;
        }

    }

    /**
     * Cria um view adapter adapter a partír de um evento.
     *
     * @param evento
     * @return
     */
    public static EventoViewAdapter criaEventoViewAdapter(Evento evento) {
        if (evento == null) {
            throw new IllegalArgumentException("Não é possível instanciar esta classe sem passar o evento.");
        }

        return new EventoViewAdapter(evento.getTitulo(), evento.getData(), evento.getLink());
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

    public boolean isClicavel() {
        return clicavel;
    }

}
