package com.inditex.price.outside.adapters.out.forfilteringprices.persistance;

import com.inditex.price.application.domain.Price;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper
public interface PersistenceMapper {

  PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

  Price toPrice(PriceEntity val);

}
