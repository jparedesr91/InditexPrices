package com.inditex.price.application.lib.exceptions;

import com.inditex.price.application.lib.exceptions.model.Error;

public class NotFoundException extends ApplicationException {

    public NotFoundException(Error e) {
        super(e);
    }

}
