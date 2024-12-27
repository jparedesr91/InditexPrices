package com.inditex.price.outside.adapters.in.forfilteringprices.web;

import com.inditex.price.adapters.in.forfiltering.ErrorDTO;
import com.inditex.price.adapters.in.forfiltering.FindByProductRequestDTO;
import com.inditex.price.adapters.in.forfiltering.FindByProductResponseDTO;
import com.inditex.price.adapters.in.forfiltering.PriceDTO;
import com.inditex.price.application.lib.exceptions.model.Error;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductQuery;
import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import org.mapstruct.factory.Mappers;
import com.inditex.price.adapters.in.forfiltering.FindByProductResponseDTO.ResponseStatusEnum;

import java.util.List;

@org.mapstruct.Mapper
public interface WebMapper {

    WebMapper MAPPER = Mappers.getMapper(WebMapper.class);

    FindByProductQuery toFindByProductQuery(FindByProductRequestDTO val);

    default FindByProductResponseDTO toFindByProductResponse(FindByProductResult val) {
        FindByProductResponseDTO responseDTO = new FindByProductResponseDTO();
        responseDTO.setPrice(toPriceDTO(val));
        responseDTO.setResponseStatus(ResponseStatusEnum.SUCCESS);
        responseDTO.setErrors(null);
        return responseDTO;
    }

    PriceDTO toPriceDTO(FindByProductResult val);

    ErrorDTO toErrorDTO(Error val);

    List<ErrorDTO> toErrorDTO(List<Error> val);
}
