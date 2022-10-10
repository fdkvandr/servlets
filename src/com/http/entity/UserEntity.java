package com.http.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private Integer id;
    private String name;
    private LocalDate birthdate;
    private String email;
    private String password;
    private UserRole role;
    private UserGender gender;
}
