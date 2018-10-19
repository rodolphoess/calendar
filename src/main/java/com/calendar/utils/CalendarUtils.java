package com.calendar.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    /**
     * Método responsável por verificar se dois períodos informados concomitam entre si.<br />
     * OBS: Caso alguma das datas seja nula, será considerado que é um intervalo aberto, ou seja,
     * considerando que:
     *  - inicio1 = 01/05/2008; termino1 = 31/05/2008; inicio2 = null; termino2 = 05/05/2008;
     * será considerado que inicio2 é anterior a inicio1, e como termino2 é posterior a inicio1 a data conflitará.
     * @param inicio1
     * @param termino1
     * @param inicio2
     * @param termino2
     * @return
     */
    public static boolean isIntervalosDeDatasConflitantes(Date inicio1, Date termino1, Date inicio2, Date termino2){

        if(inicio2 == null && termino2 == null){
            return true;
        }

        if(inicio1 == null && termino1 == null){
            return true;
        }

        if(inicio1 == null && inicio2 == null)
            return true;

        if(termino1 == null && termino2 == null)
            return true;

        if(inicio1 == null && termino2 == null){
            if(termino1 != null && !termino1.before(inicio2))
                return true;
        }

        if(termino1 == null && inicio2 == null){
            if(inicio1 != null && !inicio1.after(termino2))
                return true;
        }

        if(termino1 == null){
            if(inicio1 != null && !inicio1.after(termino2))
                return true;
        }else if(termino2 == null){
            if(!termino1.before(inicio2))
                return true;
        }else if(inicio1 == null){
            if(!termino1.before(inicio2))
                return true;
        }else if(inicio2 == null){
            if(!termino2.before(inicio1))
                return true;
        }else{
            if(!termino1.after(termino2) && !termino1.before(inicio2))
                return true;
            if(inicio1.before(inicio2) && termino1.after(termino2))
                return true;
            if(!inicio1.after(termino2) && !inicio1.before(inicio2))
                return true;
            if (inicio1.equals(inicio2) && termino1.equals(termino2))
                return true;
        }

        return false;
    }

    /**
     * Deixa um objeto Date apenas com os campos de dia, mês e ano.
     * @param data
     * @return
     */
    public static Date descartarHoras(Date data) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c = descartarHoras(c);
        return c.getTime();
    }

    /**
     * Deixa um objeto Date apenas com os campos de dia, mês e ano.
     *
     * @return
     */
    public static Calendar descartarHoras(Calendar c) {
        c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c;
    }

    /**
     * Cria uma data a partir do dia, mês e ano passados como parâmetro.
     * @param dia
     * @param mes
     * @param ano
     * @return
     */
    public static Date createDate(int dia, int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * Retorna o mês de uma data em seu formado de descrição abreviado
     *
     * @param d
     * @return
     */
    public static String getNomeMes(Date d) {
        if (d != null) {
            // MMMMM é o formato para o nome do mês por extenso
            DateFormat df = new SimpleDateFormat("MMMMM");
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            return df.format(cal.getTime());
        }

        return null;
    }

    /**
     * Retorna o nome por extenso do mês. Deve-se passar mes entre 1 e 23
     * @param mes
     * @return
     */
    public static String getNomeMes(int mes) {
        Calendar cal = Calendar.getInstance();
        mes--;
        cal.set(Calendar.MONTH, mes);
        // seta o calendário para o primeiro dia do mês
        cal.set(Calendar.DATE, 1);
        return getNomeMes(cal.getTime());
    }

    /**
     * Retorna uma data contendo o último dia do mês e ano passados
     *
     * @param mes
     * @param ano
     * @return
     */
    public static Date getMaximoDia(int mes, int ano) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ano);
        cal.set(Calendar.MONTH, mes > 0 ? mes - 1 : mes);
        // primeiro dia do mês
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar novo = Calendar.getInstance();
        novo.setTime(cal.getTime());
        int max = novo.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, max);

        return cal.getTime();
    }

    /**
     * Retorna o dia de uma determinada data. Se a data passada for nula, retorna -1.
     * @param d
     * @return
     */
    public static int getDiaByData(Date d) {
        if (d != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            return cal.get(Calendar.DATE);
        }
        return -1;
    }

    /**
     * Retorna o dia da semana de uma data. Se a data passada for nula, retorna -1.
     *
     * @param d
     * @return
     */
    public static int getDiaSemanaByData(Date d) {
        if (d != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            return cal.get(Calendar.DAY_OF_WEEK);
        }
        return -1;
    }

    /**
     * Retorna o mês de uma determinada data. Se a data passada for nula, retorna -1.
     * @param d
     * @return
     */
    public static int getMesByData(Date d) {
        if (d != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            return cal.get(Calendar.MONTH);
        }
        return -1;
    }

    /**
     * Retorna o n�mero da semana no ano associada ao dia passado por par�metro.
     *
     * @param dia
     * @return
     */
    public static int getNumeroSemana(Date dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
