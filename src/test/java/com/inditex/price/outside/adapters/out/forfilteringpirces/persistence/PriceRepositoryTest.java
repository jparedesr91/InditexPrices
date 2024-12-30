package com.inditex.price.outside.adapters.out.forfilteringpirces.persistence;

import com.inditex.price.outside.adapters.out.forobtainprices.persistance.BrandEntity;
import com.inditex.price.outside.adapters.out.forobtainprices.persistance.PriceEntity;
import com.inditex.price.outside.adapters.out.forobtainprices.persistance.PriceRepository;
import com.inditex.price.outside.adapters.out.forobtainprices.persistance.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void obtainPrice() {
        PriceEntity expected = getPriceEntity();
        PriceEntity result = priceRepository.findByProductIdBrandIdAndApplicationDate(35455L, 1L, LocalDateTime.of(2020, 6, 14, 17, 0, 0));
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
        assertEquals(expected.getPriority(), result.getPriority());
        assertEquals(expected.getProduct().getId(), result.getProduct().getId());
        assertEquals(expected.getBrand().getId(), result.getBrand().getId());
        assertEquals(expected.getCurrency(), result.getCurrency());
        assertEquals(expected.getPrice(), result.getPrice());
    }

    private PriceEntity getPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(2L);
        priceEntity.setBrand(new BrandEntity(1L, "ZARA"));
        priceEntity.setProduct(new ProductEntity(35455L, "SHOES"));
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        priceEntity.setPrice(25.45);
        priceEntity.setCurrency("EUR");
        priceEntity.setPriority(1);
        return priceEntity;
    }
}
