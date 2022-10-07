package com.http.dto;

import java.util.Objects;

public class FlightDto {

    private final Long id;
    private final String descriprion;

    public FlightDto(Long id, String descriprion) {
        this.id = id;
        this.descriprion = descriprion;
    }

    public Long getId() {
        return id;
    }

    public String getDescriprion() {
        return descriprion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto flightDto = (FlightDto) o;
        return Objects.equals(id, flightDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "id=" + id +
                ", descriprion='" + descriprion + '\'' +
                '}';
    }
}
