package com.http.service;

import com.http.dao.FlightDao;
import com.http.dto.FlightDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightService {

    @Getter
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getINSTANCE();

    public List<FlightDto> findAll() {
        return flightDao.findAll()
                        .stream()
                        .map(flightEntity -> FlightDto.builder()
                                                      .id(flightEntity.getId())
                                                      .description("""
                                                                      %s - %s - %s
                                                                   """.formatted(flightEntity.getDeparture_airportCode(), flightEntity.getArrival_airportCode(), flightEntity.getStatus()))
                                                      .build()
                        )
                        .collect(toList());
    }
}
