package ta.transaction.command.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import ta.transaction.command.domain.service.CreateProductService;
import ta.transaction.command.domain.service.DeleteProductService;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductDeleteEvent;

import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteCommandController.class)
class DeleteCommandControllerTest {

    @MockBean
    private DeleteProductService createProductService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProduct() throws Exception, ProductNotFound {
        String productID="12345";

        Mockito.when(createProductService.delete(productID)).thenReturn(new ProductDeleteEvent("success"));

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/e-auction/api/v1/seller/delete/12345")

                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }
}