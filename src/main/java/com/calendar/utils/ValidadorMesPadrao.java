package com.calendar.utils;

import java.util.Calendar;
import java.util.Date;


class ValidadorMesPadrao implements ValidadorMes{

    private final int mes;
   
    /**
     * Construtor para a validação do mês.
     * 
     * @param mes Valor do mês na api <code>java.util.Calendar</code>
     */
    public ValidadorMesPadrao(int mes) {
        this.mes = mes;
    }

    @Override
    public boolean dataPertenceAoMes(Date data) {
       int dia = CalendarUtils.getDiaByData(data);
       int mesData = CalendarUtils.getMesByData(data);
       
       if(mes != mesData) {
    	   return false;
       }
       
       if (isMesTrintaeUm()) {
    	   if(dia>=1 && dia<=31) {
    		   return true;
    	   }
       }else if(dia>=1 && dia<=30) {
    	   return true;
       }
       
       return false;
    }
    
    /**
     * 
     * 
     * @return
     */
    private boolean isMesTrintaeUm() {
    	//Entre Janeiro até julho, um mês (excetuando fevereiro possui 31 dias se o mes mod 2 = 0. 
    	if(mes <= 6) {
    		return mes % 2 == 0;
    	}else {
    		return mes % 2 == 1;
    	}    
    }

}