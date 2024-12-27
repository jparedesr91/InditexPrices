package com.inditex.price.functional.steps;

import com.inditex.price.InditexPriceApplication;
import com.inditex.price.adapters.in.forfiltering.FindByProductRequestDTO;
import com.inditex.price.adapters.in.forfiltering.FindByProductResponseDTO;
import com.inditex.price.functional.FilterControllerTextContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = InditexPriceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilterControllerSteps {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    private final FilterControllerTextContext filterControllerTextContext = new FilterControllerTextContext();

    @Given("The folling resquests for filtering prices")
    public void makeRequest(DataTable dataTable) {
        makeRequest(dataTable.asMaps().get(0));
    }

    @Then("I should be able to get price by product, brand and application date")
    public void assertPriceMatches(DataTable dataTable) {
           assertEquals(Double.parseDouble(dataTable.asMaps().get(0).get("expectedPrice")),
                   Objects.requireNonNull(filterControllerTextContext.getResponse().getBody()).getPrice().getPrice());
    }

    @And("I should receive response with status code")
    public void assertResponseStatusCode(DataTable dataTable) {
        assertEquals(HttpStatusCode.valueOf(Integer.parseInt(dataTable.asMaps().get(0).get("statusCode"))),
                filterControllerTextContext.getResponse().getStatusCode());
    }

    @Then("I should get not found resource")
    public void assertNotFound() {
        assertEquals(HttpStatusCode.valueOf(404), filterControllerTextContext.getResponse().getStatusCode());
    }

    @Then("I should get bad request")
    public void assertBadRequest() {
        assertEquals(HttpStatusCode.valueOf(400), filterControllerTextContext.getResponse().getStatusCode());
    }

    public void makeRequest(Map<String, String> request) {
        String productId = request.get("productId");
        Long productIdParam = Strings.isBlank(productId) ? null : Long.parseLong(productId);
        String brandId = request.get("brandId");
        Long brandIdParam = Strings.isBlank(brandId) ? null : Long.parseLong(brandId);
        String applicationDate = request.get("applicationDate");
        filterControllerTextContext.setResponse(getPrice(productIdParam, brandIdParam, applicationDate));
    }

    public ResponseEntity<FindByProductResponseDTO> getPrice(Long productId, Long brandId, String applicationDate) {
        FindByProductRequestDTO findByProductRequestDTO = new FindByProductRequestDTO();
        findByProductRequestDTO.setApplicationDate(applicationDate);
        findByProductRequestDTO.setBrandId(brandId);
        findByProductRequestDTO.setProductId(productId);
        return testRestTemplate.postForEntity("/filter", findByProductRequestDTO, FindByProductResponseDTO.class);
    }
}