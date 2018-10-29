package com.calendar.service;

import com.calendar.domain.CalendarioImpl;
import com.calendar.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalendarService {

    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public void cadastrarCalanedario(){

    }

    public List<CalendarioImpl> listarCalendarios(){
        return null;
    }


}
