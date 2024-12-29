package com.inditex.price.application.lib.mapper;

import com.inditex.price.application.domain.Brand;
import com.inditex.price.application.domain.Price;
import com.inditex.price.application.domain.Product;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ApplicationMapperTest {

  private final ApplicationMapper mapper = Mappers.getMapper(ApplicationMapper.class);

  @Test
  public void testMappingFromPriceToFindByProductResult() {
    Price price = getPrice();
    FindByProductResult result = mapper.toFindByProductResult(price);
    assertNotNull("FindByProductResult should not be null", result);
    assertEquals(35455L, result.productId());
    assertEquals(1L, result.brandId());
    assertEquals(LocalDateTime.of(2022, 1, 1, 12, 0, 0), result.startDate());
    assertEquals(LocalDateTime.of(2022, 12, 31, 12, 0, 0), result.endDate());
    assertEquals(35.50, result.price());
  }

  private Price getPrice() {
    Price price = new Price();
    price.setId(0L);
    price.setBrand(new Brand(1L, "ZARA"));
    price.setProduct(new Product(35455L, "SHOES"));
    price.setStartDate(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
    price.setEndDate(LocalDateTime.of(2022, 12, 31, 12, 0, 0));
    price.setPrice(35.50);
    price.setCurrency("EUR");
    price.setPriority(0);
    return price;
  }

}
