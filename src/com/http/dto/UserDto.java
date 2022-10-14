package com.http.dto;

import com.http.entity.UserGender;
import com.http.entity.UserRole;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    UserRole role;
    UserGender gender;
}
