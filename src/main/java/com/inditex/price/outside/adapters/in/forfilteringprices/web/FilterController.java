package com.inditex.price.outside.adapters.in.forfilteringprices.web;

import com.inditex.price.adapters.in.forfiltering.FilterApi;
import com.inditex.price.adapters.in.forfiltering.FindByProductRequestDTO;
import com.inditex.price.adapters.in.forfiltering.FindByProductResponseDTO;
import com.inditex.price.application.ports.in.forfilteringprices.ForFilteringPrices;
import com.inditex.price.outside.lib.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import static com.inditex.price.outside.adapters.in.forfilteringprices.web.WebMapper.MAPPER;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class FilterController implements FilterApi {

    private final ForFilteringPrices forFilteringPrices;

    @Override
    public ResponseEntity<FindByProductResponseDTO> findByProduct(FindByProductRequestDTO findByProductRequestDTO) {
        return ResponseEntity
                .ok(MAPPER.toFindByProductResponse(
                        forFilteringPrices.findByProduct(
                                MAPPER.toFindByProductQuery(findByProductRequestDTO))));
    }
}