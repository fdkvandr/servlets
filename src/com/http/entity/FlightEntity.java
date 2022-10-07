package com.http.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class FlightEntity {

    private Long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private String departure_airportCode;
    private LocalDateTime arrivalDate;
    private String arrival_airportCode;
    private Integer artifactId;
    private FlightStatus status;
}
