package com.inditex.price.application.ports.in.forfilteringprices;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class FindByProductQueryTest {

  @Test
  void validationOk() {
    new FindByProductQuery(
        1L,
        1L,
        LocalDateTime.of(2022, 6, 15, 6, 0, 0));
  }

  @Test
  void productIdFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductQuery(
          null,
          1L,
          LocalDateTime.of(2022, 6, 15, 6, 0, 0));
    });
  }

  @Test
  void brandIdFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductQuery(
          1L,
          null,
          LocalDateTime.of(2022, 6, 15, 6, 0, 0));
    });
  }

  @Test
  void applicationDateFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductQuery(
          1L,
          1L,
          null);
    });
  }

}
