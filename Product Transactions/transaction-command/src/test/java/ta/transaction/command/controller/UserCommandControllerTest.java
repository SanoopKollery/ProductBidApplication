package ta.transaction.command.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ta.transaction.command.domain.model.Transaction;
import ta.transaction.command.domain.service.CreateTransactionService;
import ta.transaction.command.dto.CreateTransactionRequest;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionCreatedEvent;

import javax.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserCommandController.class)
class UserCommandControllerTest {

    @MockBean
    private CreateTransactionService createTransactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProduct() throws Exception,TransactionExistsException, ProductNotFound, URISyntaxException {

        CreateTransactionRequest c= CreateTransactionRequest.builder().build();
        c.setProductId("333");
      c.setAddress("abcdefg");
      c.setBidAmount(33.00);
      c.setCity("kolkata");
      c.setEmail("abc@gmail.com");
      c.setFirstName("somnath");
      c.setLastName("poddar");
      c.setPhone("7044086975");
      c.setPin("700028");
      c.setState("westBengal");
        String jsonRequest="{\n" +
                "    \"firstName\": \"Kiran\",\n" +
                "    \"lastName\": \"Kiran\",\n" +
                "    \"address\": \"TestAddress\",\n" +
                "    \"city\": \"TestCity\",\n" +
                "    \"state\": \"TestState\",\n" +
                "    \"pin\": \"12345\",\n" +
                "    \"phone\": \"1234567890\",\n" +
                "    \"email\": \"kiran@gmail.com\",\n" +
                "    \"productId\": \"7d5927a7-230e-4d52-b75e-e02a9a16f0b2\",\n" +
                "    \"bidAmount\": \"1000.00\"\n" +
                "}";

        TransactionCreatedEvent t= TransactionCreatedEvent.builder().build();
        t.setUuid(UUID.randomUUID());
        Transaction t1= Transaction.builder().build();
        t.setTransaction(t1);

        Mockito.when(createTransactionService.create(c)).thenReturn(t);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/e-auction/api/v1/buyer/place-bid")
                .content(jsonRequest)

                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated());
    }
}