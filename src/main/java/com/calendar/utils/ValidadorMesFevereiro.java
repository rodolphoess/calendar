package com.calendar.utils;

import java.util.Calendar;
import java.util.Date;

class ValidadorMesFevereiro implements ValidadorMes{

    private final int mes;
    private final int ano;

    public ValidadorMesFevereiro(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public boolean dataPertenceAoMes(Date data) {
        //se for fevereiro, deve se considerar os anos bissextos.
        //se o mes for fevereiro, verificar se o dia é maior que 28 (29 ano bissexto)
        int dia = CalendarUtils.getDiaByData(data);
        if (isAnoBissexto()) {
            if (dia >= 1 && dia <= 29) {
                return true;
            }
        } else if (dia >= 1 && dia <= 28) {
            return true;
        }

        return false;
    }

    private boolean isAnoBissexto() {
        if ((ano % 4 == 0) && (ano % 100 != 0) && (ano % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

}
