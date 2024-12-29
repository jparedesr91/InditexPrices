package com.inditex.price.application.domain;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Product {

  private Long id;
  private String name;

}
