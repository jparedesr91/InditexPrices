package com.inditex.price.application.lib.mapper;

import com.inditex.price.application.domain.Price;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper
public interface ApplicationMapper {

  ApplicationMapper MAPPER = Mappers.getMapper(ApplicationMapper.class);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "brandId", source = "brand.id")
  FindByProductResult toFindByProductResult(Price val);

}
