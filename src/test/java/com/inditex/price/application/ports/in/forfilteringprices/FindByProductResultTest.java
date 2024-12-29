package com.inditex.price.application.ports.in.forfilteringprices;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class FindByProductResultTest {

  @Test
  public void validationOk() {
    new FindByProductResult
        (35455L,
            1L,
            LocalDateTime.of(2022, 1, 1, 12, 0, 0),
            LocalDateTime.of(2022, 12, 31, 12, 0, 0),
            35.50
        );
  }

  @Test
  public void productIdValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (null,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  public void brandIdValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              null,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  public void startDateValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              null,
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  public void endDateValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              null,
              35.50
          );
    });
  }

  @Test
  public void priceValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              null
          );
    });
  }
}
