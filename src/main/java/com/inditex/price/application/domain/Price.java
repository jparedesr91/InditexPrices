package com.inditex.price.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

  private Long id;
  private Product product;
  private Brand brand;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer priority;
  private Double price;
  private String currency;

}
