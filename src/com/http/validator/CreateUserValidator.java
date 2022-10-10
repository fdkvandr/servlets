package com.http.validator;

import com.http.dto.CreateUserDto;
import com.http.entity.UserGender;
import com.http.util.LocalDateFormatter;
import lombok.Getter;

import java.time.LocalDate;

public class CreateUserValidator implements Validator<CreateUserDto> {

    @Getter
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }

        if (object.getGender() == null) {
            validationResult.add(Error.of("null.gender", "Gender is null"));
        } else {
            if (UserGender.valueOf(object.getGender()) == null)
                validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return validationResult;
    }
}
