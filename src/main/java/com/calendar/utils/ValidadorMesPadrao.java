package com.calendar.utils;

import java.util.Date;

class ValidadorMesPadrao implements ValidadorMes{

    private final int mes;

    public ValidadorMesPadrao(int mes) {
        this.mes = mes;
    }

    @Override
    public boolean dataPertenceAoMes(Date data) {
        return false;
    }
}
