package com.http.dao;

import com.http.entity.UserEntity;
import com.http.entity.UserGender;
import com.http.entity.UserRole;
import com.http.util.ConnectionManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, UserEntity> {

    @Getter
    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL =
            """
            INSERT INTO users (name, birthdate, email, image, password, role, gender) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            """
            SELECT id, name, birthdate, email, image, password, role, gender
            FROM users
            WHERE email = ? and password = ?
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
            preparedStatement.setObject(6, entity.getRole()
                                                 .name());
            preparedStatement.setObject(7, entity.getGender()
                                                 .name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            UserEntity userEntity = null;
            if (resultSet.next()) {
                userEntity = buildEntity(resultSet);
            }
            return Optional.ofNullable(userEntity);
        }

    }

    private static UserEntity buildEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                         .id(resultSet.getObject("id", Integer.class))
                         .name(resultSet.getObject("name", String.class))
                         .birthdate(resultSet.getObject("birthdate", Date.class)
                                             .toLocalDate())
                         .email(resultSet.getObject("email", String.class))
                         .image(resultSet.getObject("image", String.class))
                         .password(resultSet.getObject("password", String.class))
                         .role(UserRole.find(resultSet.getObject("role", String.class))
                                       .orElse(null))
                         .gender(UserGender.valueOf(resultSet.getObject("gender", String.class)))
                         .build();
    }
}
