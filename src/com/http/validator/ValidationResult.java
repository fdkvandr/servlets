package com.http.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    final private List<Error> errors = new ArrayList<>();

    public void add(Error error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
