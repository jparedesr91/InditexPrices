package com.inditex.price.outside.adapters.in.forfilteringpirces.web;

import com.inditex.price.application.ports.in.forfilteringprices.FindByProductResult;
import com.inditex.price.application.usecases.FindByProduct;
import com.inditex.price.outside.adapters.in.forfilteringprices.web.FilterController;
import com.inditex.price.outside.lib.utils.MessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FilterController.class)
public class FilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindByProduct findByProduct;

    @MockitoBean
    private MessageUtil messageUtil;

    @Test
    void testFindByProduct() throws Exception {
        when(findByProduct.findByProduct(any())).thenReturn(getFindByProductResult());
        String jsonStr = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-14T17:00:00"
                }
                """;
        mockMvc.perform(post("/filter")
                        .content(jsonStr)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    private FindByProductResult getFindByProductResult() {
        return new FindByProductResult
                (35455L,
                        1L,
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        35.50
                );
    }
}
