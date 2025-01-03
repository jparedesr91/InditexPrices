package com.inditex.price.application.ports.out.forobtainprices;

import com.inditex.price.application.domain.Price;
import java.time.LocalDateTime;

public interface ForObtainPrices {

  Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate);

}
