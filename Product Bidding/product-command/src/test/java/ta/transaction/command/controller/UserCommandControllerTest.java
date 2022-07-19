package ta.transaction.command.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ta.transaction.command.domain.service.CreateProductService;
import ta.transaction.command.domain.service.UpdateProductService;
import ta.transaction.command.dto.CreateProductRequest;
import ta.transaction.command.exception.FutureDateException;
import ta.transaction.command.exception.ProductCategoryException;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductCreatedEvent;

import javax.ws.rs.core.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserCommandController.class)
class UserCommandControllerTest {

    @MockBean
    private CreateProductService createProductService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProduct() throws  Exception, ProductCategoryException, FutureDateException {

        CreateProductRequest c= CreateProductRequest.builder().build();
        c.setProductId("333");
        c.setProductName("abcdef");
        c.setDescription("efg");
        c.setShortDescription("qwer");
        c.setCategory("ORNAMENT");
        c.setStartingPrice(new BigDecimal( 33.00));
        c.setBidEndDate("2022-11-25T14:05:15.953Z");
        String jsonRequest="{  \"productId\":333,\n" +
                "    \"productName\": \"abcdefg\",\n" +
                "    \"shortDescription\": \"def\",\n" +
                "    \"description\": \"ghi\",\n" +
                "    \"category\": \"ORNAMENT\",\n" +
                "    \"startingPrice\": 333.00,\n" +
                "    \"bidEndDate\": \"2022-11-25T14:05:15.953Z\"\n" +
                "}";

        Mockito.when(createProductService.create(c)).thenReturn(ProductCreatedEvent.builder().build());

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/e-auction/api/v1/seller/add-product")
                .content(jsonRequest)

                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated());
    }
}