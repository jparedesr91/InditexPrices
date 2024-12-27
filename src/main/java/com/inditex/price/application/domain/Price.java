package com.inditex.price.application.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
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
