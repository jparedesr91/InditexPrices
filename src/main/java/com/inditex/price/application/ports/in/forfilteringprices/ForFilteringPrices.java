package com.inditex.price.application.ports.in.forfilteringprices;

public interface ForFilteringPrices {
    FindByProductResult findByProduct(FindByProductQuery findByProductQuery);
}