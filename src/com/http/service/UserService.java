package com.http.service;

import com.http.dao.UserDao;
import com.http.dto.CreateUserDto;
import com.http.entity.UserEntity;
import com.http.exceptions.ValidationException;
import com.http.mapper.CreateUserMapper;
import com.http.validator.CreateUserValidator;
import com.http.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    @Getter
    private static final UserService INSTANSE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getINSTANCE();
    private final UserDao userDao = UserDao.getINSTANCE();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getINSTANCE();
    public Integer create(CreateUserDto createUserDto){

        //validation
        //map
        //userDao to save
        //return id

        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = createUserMapper.mapFrom(createUserDto);

        userDao.save(userEntity);

        return userEntity.getId();
    }
}
