package com.inditex.price.outside.adapters.out.forobtainprices.persistance;

import com.inditex.price.application.domain.Price;
import com.inditex.price.application.ports.out.forobtainprices.ForObtainPrices;
import com.inditex.price.outside.lib.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import static com.inditex.price.outside.adapters.out.forobtainprices.persistance.PersistenceMapper.MAPPER;

@PersistenceAdapter
@RequiredArgsConstructor
public class ObtainPrices implements ForObtainPrices {

  private final PriceRepository priceRepository;

  @Override
  public Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate) {
    return MAPPER.toPrice(
        priceRepository.findByProductIdBrandIdAndApplicationDate(productId, brandId,
            applicationDate));
  }

}