package com.inditex.price.outside.adapters.out.forfilteringprices.persistance;

import com.inditex.price.application.domain.Price;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface PersistenceMapper {

    PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

    Price toPrice(PriceEntity val);
}
