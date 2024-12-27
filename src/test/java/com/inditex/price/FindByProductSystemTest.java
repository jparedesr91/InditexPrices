package com.inditex.price;

import com.inditex.price.adapters.in.forfiltering.FindByProductRequestDTO;
import com.inditex.price.adapters.in.forfiltering.FindByProductResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindByProductSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findByProduct() {
        ResponseEntity<FindByProductResponseDTO> response = whenFindByProduct(getFindByProductRequestDTO());
        then(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

    }

    private ResponseEntity<FindByProductResponseDTO> whenFindByProduct(FindByProductRequestDTO findByProductRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<FindByProductRequestDTO> request = new HttpEntity<>(findByProductRequestDTO, headers);

        return restTemplate.exchange(
                "/filter",
                HttpMethod.POST,
                request,
                FindByProductResponseDTO.class);
    }

    FindByProductRequestDTO getFindByProductRequestDTO() {
        FindByProductRequestDTO findByProductRequestDTO = new FindByProductRequestDTO();
        findByProductRequestDTO.setProductId(35455L);
        findByProductRequestDTO.setBrandId(1L);
        findByProductRequestDTO.setApplicationDate("2020-06-14T17:00:00");
        return findByProductRequestDTO;
    }
}
