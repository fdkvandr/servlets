package com.http.mapper;

import com.http.dto.CreateUserDto;
import com.http.entity.UserEntity;
import com.http.entity.UserGender;
import com.http.entity.UserRole;
import com.http.util.LocalDateFormatter;
import lombok.Getter;

public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    @Getter
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                               .name(object.getName())
                               .birthdate(LocalDateFormatter.format(object.getBirthday()))
                               .email(object.getEmail())
                               .password(object.getPassword())
                               .gender(UserGender.valueOf(object.getGender()))
                               .role(UserRole.valueOf(object.getRole()))
                               .build();
    }
}
