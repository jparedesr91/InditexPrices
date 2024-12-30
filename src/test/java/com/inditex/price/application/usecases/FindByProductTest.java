package com.inditex.price.application.usecases;

import com.inditex.price.application.domain.Brand;
import com.inditex.price.application.domain.Price;
import com.inditex.price.application.domain.Product;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductQuery;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import com.inditex.price.application.lib.exceptions.NotFoundException;
import com.inditex.price.application.ports.out.forobtainprices.ForObtainPrices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.time.LocalDateTime;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindByProductTest {

  @Mock
  private ForObtainPrices forObtainPrices;

  @InjectMocks
  private FindByProduct forFilteringPrices;

  @Test
  void Given_PricesNotFound_When_GetByProduct_Then_ReturnError() {
    when(forObtainPrices.findByProduct(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(
        null);
    assertThrows(NotFoundException.class, ()
        -> forFilteringPrices
        .findByProduct(getFindByProductQuery()));
    verify(forObtainPrices).findByProduct(anyLong(), anyLong(), any(LocalDateTime.class));
  }

  @Test
  void Given_PricesFound_When_GetByProduct_Then_ReturnOk() {
    when(forObtainPrices.findByProduct(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(
        getPrice());
    assertEquals(getFindByProductResult(),
        forFilteringPrices.findByProduct(getFindByProductQuery()));
    verify(forObtainPrices).findByProduct(anyLong(), anyLong(), any(LocalDateTime.class));
  }

  private FindByProductQuery getFindByProductQuery() {
    return new FindByProductQuery(
        1L,
        1L,
        LocalDateTime.of(2022, 6, 15, 6, 0, 0)
    );
  }

  private FindByProductResult getFindByProductResult() {
    return new FindByProductResult
        (35455L,
            1L,
            LocalDateTime.of(2022, 1, 1, 12, 0, 0),
            LocalDateTime.of(2022, 12, 31, 12, 0, 0),
            35.50
        );
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
