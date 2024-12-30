package com.inditex.price.outside;

import com.inditex.price.application.ports.in.forfilteringprices.ForFilteringPrices;
import com.inditex.price.application.ports.out.forobtainprices.ForObtainPrices;
import com.inditex.price.application.usecases.FindByProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurator {

  @Bean
  ForFilteringPrices forFilteringPrices(ForObtainPrices forObtainPrices) {
    return new FindByProduct(forObtainPrices);
  }

}
