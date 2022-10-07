package com.http.dao;

import com.http.entity.TicketEntity;
import com.http.util.ConnectionManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, TicketEntity> {

    @Getter
    private static final TicketDao INSTANCE = new TicketDao();
    private static final String FIND_ALL_BY_FLIGHT_ID_SQL = """
            SELECT *
            FROM ticket
            WHERE flight_id = ?
            """;

    private TicketDao() {
    }

    @SneakyThrows
    public List<TicketEntity> findAllByFlightId(Long flightId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID_SQL)) {
            preparedStatement.setObject(1, flightId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<TicketEntity> ticketEntities = new ArrayList<>();
            while (resultSet.next()) {
                ticketEntities.add(buildTicketEntity(resultSet));
            }

            return ticketEntities;
        }
    }

    @Override
    public List<TicketEntity> findAll() {
        return null;
    }

    @Override
    public Optional<TicketEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(TicketEntity entity) {

    }

    @Override
    public TicketEntity save(TicketEntity entity) {
        return null;
    }

    @SneakyThrows
    private TicketEntity buildTicketEntity(ResultSet resultSet) {
        return new TicketEntity(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }
}
