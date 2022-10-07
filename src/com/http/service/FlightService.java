package com.http.service;

import com.http.dao.FlightDao;
import com.http.dto.FlightDto;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {
    @Getter
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getINSTANCE();

    private FlightService() {
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flightEntity -> FlightDto.builder()
                        .id(flightEntity.getId())
                        .descriprion("""
                                        %s - %s - %s
                                     """.formatted(flightEntity.getDeparture_airportCode(), flightEntity.getArrival_airportCode(), flightEntity.getStatus()))
                        .build()
                )
                .collect(toList());
    }
}
