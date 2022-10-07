package com.http.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightEntity {

    private Long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private String departure_airportCode;
    private LocalDateTime arrivalDate;
    private String arrival_airportCode;
    private Integer artifactId;
    private FlightStatus status; // Создадим enum для отображения всех возможных статусов

    public FlightEntity(Long id, String flightNo, LocalDateTime departureDate, String departure_airportCode, LocalDateTime arrivalDate, String arrival_airportCode, Integer artifactId, FlightStatus status) {
        this.id = id;
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.departure_airportCode = departure_airportCode;
        this.arrivalDate = arrivalDate;
        this.arrival_airportCode = arrival_airportCode;
        this.artifactId = artifactId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id=" + id +
                ", flightNo='" + flightNo + '\'' +
                ", departureDate=" + departureDate +
                ", departure_airportCode='" + departure_airportCode + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", arrival_airportCode='" + arrival_airportCode + '\'' +
                ", artifactId=" + artifactId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightEntity that = (FlightEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeparture_airportCode() {
        return departure_airportCode;
    }

    public void setDeparture_airportCode(String departure_airportCode) {
        this.departure_airportCode = departure_airportCode;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrival_airportCode() {
        return arrival_airportCode;
    }

    public void setArrival_airportCode(String arrival_airportCode) {
        this.arrival_airportCode = arrival_airportCode;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}
