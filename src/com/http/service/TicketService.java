package com.http.service;

import com.http.dao.FlightDao;
import com.http.dao.TicketDao;
import com.http.dto.TicketDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {

    @Getter
    private static TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getINSTANCE();

    public TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticketEntity -> TicketDto.builder()
                        .id(ticketEntity.getId())
                        .flightId(ticketEntity.getFlightId())
                        .seatNo(ticketEntity.getSeatNo())
                        .build()
                )
                .collect(toList());
    }
}
