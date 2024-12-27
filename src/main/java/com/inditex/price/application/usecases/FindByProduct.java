package com.inditex.price.application.usecases;

import com.inditex.price.application.domain.Price;
import com.inditex.price.application.lib.exceptions.NotFoundException;
import com.inditex.price.application.lib.exceptions.model.ErrorType;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductQuery;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import com.inditex.price.application.ports.in.forfilteringprices.ForFilteringPrices;
import com.inditex.price.application.lib.UseCase;
import com.inditex.price.application.ports.out.forfilteringprices.ForObtainPrices;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import java.util.Objects;

import static com.inditex.price.application.lib.mapper.ApplicationMapper.MAPPER;

@UseCase
@RequiredArgsConstructor
public class FindByProduct implements ForFilteringPrices {

    private final ForObtainPrices filteringPrices;

    @Override
    @SneakyThrows
    public FindByProductResult findByProduct(FindByProductQuery findByProductQuery) {
        Price price = filteringPrices.findByProduct(
                                            findByProductQuery.productId(),
                                            findByProductQuery.brandId(),
                                            findByProductQuery.applicationDate());
        if (Objects.isNull(price)) {
            throw new NotFoundException(ErrorType.RESOURCE_NOT_FOUND.toError());
        } else {
            return MAPPER.toFindByProductResult(price);
        }
    }
}
