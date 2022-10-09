package com.http.dao;

import com.http.entity.FlightEntity;
import com.http.entity.FlightStatus;
import com.http.util.ConnectionManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, FlightEntity> {

    @Getter
    private static final FlightDao INSTANCE = new FlightDao();
    public static final String FIND_ALL_SQL = """
            SELECT *
            FROM flight
            """;

    private FlightDao() {
    }

    @SneakyThrows
    @Override
    public List<FlightEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<FlightEntity> flightEntities = new ArrayList<>();
            while (resultSet.next()) {
                flightEntities.add(buildFlightEntity(resultSet));
            }

            return flightEntities;
        }
    }

    @Override
    public Optional<FlightEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(FlightEntity entity) {

    }

    @Override
    public FlightEntity save(FlightEntity entity) {
        return null;
    }

    @SneakyThrows
    private FlightEntity buildFlightEntity(ResultSet resultSet) {
        return new FlightEntity(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class)
                         .toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class)
                         .toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }
}
