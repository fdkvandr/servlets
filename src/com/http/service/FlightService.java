package com.http.service;

import com.http.dao.FlightDao;
import com.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flightEntity -> new FlightDto(
                        flightEntity.getId(),
                        """
                            %s - %s - %s
                        """.formatted(flightEntity.getDeparture_airportCode(), flightEntity.getArrival_airportCode(), flightEntity.getStatus())
                ))
                .collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
