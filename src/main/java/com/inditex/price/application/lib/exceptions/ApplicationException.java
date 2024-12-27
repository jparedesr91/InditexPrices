package com.inditex.price.application.lib.exceptions;

import com.inditex.price.application.lib.exceptions.model.Error;

import java.util.ArrayList;
import java.util.Collection;

public class ApplicationException extends RuntimeException {

    private final Collection<Error> errors;

    public ApplicationException(Error e) {
        super(e.getMessage());
        this.errors = new ArrayList<>();
        this.errors.add(e);
    }

    public Collection<Error> getErrors() {
        return this.errors;
    }

}
