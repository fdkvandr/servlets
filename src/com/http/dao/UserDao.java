package com.http.dao;

import com.http.entity.UserEntity;
import com.http.util.ConnectionManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, UserEntity> {

    @Getter
    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
                                           INSERT INTO users (name, birthdate, email, image, password, role, gender) 
                                           VALUES (?, ?, ?, ?, ?, ?, ?)
                                           """;

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @SneakyThrows
    @Override
    public UserEntity save(UserEntity entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthdate());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getImage());
            preparedStatement.setObject(5, entity.getPassword());
            preparedStatement.setObject(6, entity.getRole().name());
            preparedStatement.setObject(7, entity.getGender().name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }
}
