package com.inditex.price.outside.adapters.out.forfilteringpirces.persistence;

import com.inditex.price.application.domain.Brand;
import com.inditex.price.application.domain.Price;
import com.inditex.price.application.domain.Product;
import com.inditex.price.outside.adapters.out.forfilteringprices.persistance.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ObtainPricesTest {

    private final PriceRepository priceRepository = Mockito.mock(PriceRepository.class);
    private final ObtainPrices obtainPrices = new ObtainPrices(this.priceRepository);

    @Test
    void Given_Prices_When_GetByProduct_Then_ReturnPrice() {
        when(priceRepository.findByProductIdBrandIdAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(getPriceEntity());
        Price expected = getPrice();
        Price result = obtainPrices.findByProduct(35455L, 1L, LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        assertEquals(expected,result);
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

    private PriceEntity getPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(0L);
        priceEntity.setBrand(new BrandEntity(1L, "ZARA"));
        priceEntity.setProduct(new ProductEntity(35455L, "SHOES"));
        priceEntity.setStartDate(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2022, 12, 31, 12, 0, 0));
        priceEntity.setPrice(35.50);
        priceEntity.setCurrency("EUR");
        priceEntity.setPriority(0);
        return priceEntity;
    }
}
