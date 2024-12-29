package com.inditex.price.application.lib.exceptions;

import com.inditex.price.application.lib.exceptions.model.Error;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class ApplicationException extends RuntimeException {

  private final Collection<Error> errors;

  public ApplicationException(Error e) {
    super(e.getMessage());
    this.errors = new ArrayList<>();
    this.errors.add(e);
  }

}
