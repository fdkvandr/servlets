package com.http.service;

import com.http.dao.FlightDao;
import com.http.dao.TicketDao;
import com.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {

    private static TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public TicketService() {
    }

    public static TicketService getINSTANCE() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticketEntity -> new TicketDto(
                        ticketEntity.getId(),
                        ticketEntity.getFlightId(),
                        ticketEntity.getSeatNo()
                ))
                .collect(toList());
    }
}
