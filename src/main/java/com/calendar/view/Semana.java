package com.calendar.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
* Classe que representa uma semana do calendário.
*
* @author Daniel Smith
*
*/
public final class Semana {

    /**
     * Representa o dia que a semana vai começar.
     *
     * Começa de 1 e termina em 7, sendo 1 correspontente a DOMINGO.
     *
     * @see Calendar#DAY_OF_WEEK
     */
    public Integer diaInicial;

    /**
     * Atributo que representa os dias da semana.
     */
    public Collection<Dia> dias;

    public Semana() {
        this.dias = new ArrayList<Dia>();
        this.diaInicial = 0;
    }

    /**
     * Adiciona um dia ao calendário.
     *
     * @param dia
     */
    public void addDia(Dia dia) {
        if(dia == null) {
            throw new IllegalArgumentException("Dia não pode ser nulo");
        }

        final Integer diaDaSemana = dia.getDiaDaSemana();

        if(possuiDiaSemana(diaDaSemana)) {
            throw new IllegalArgumentException("Dia já inserido. Utilize o método possuiDiaSemana antes de chamar este método.");
        }

        dias.add(dia);

        if(diaInicial > diaDaSemana) {
            diaInicial = diaDaSemana;
        }
    }

    /**
     * Retorna se a semana já possui o dia da semana passado por parâmetro.
     *
     * @param diaDaSemana
     * @return
     */
    public boolean possuiDiaSemana(int diaDaSemana) {

        for (Dia dia : dias) {
            if(dia.getDiaDaSemana() == diaDaSemana) {
                return true;
            }
        }

        return false;

    }

    public Dia getDia(Integer diaDaSemana) {

        for (Dia dia : dias) {
            if(diaDaSemana == dia.getDiaDaSemana()) {
                return dia;
            }
        }

        return null;
    }

    public Collection<Dia> getDias() {
        return dias;
    }

}
