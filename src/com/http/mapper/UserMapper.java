package com.http.mapper;

import com.http.dto.UserDto;
import com.http.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserEntity, UserDto> {

    @Getter
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                      .id(object.getId())
                      .name(object.getName())
                      .birthday(object.getBirthdate())
                      .email(object.getEmail())
                      .image(object.getImage())
                      .role(object.getRole())
                      .gender(object.getGender())
                      .build();
    }
}
