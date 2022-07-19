package ta.transaction.command.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ta.transaction.command.domain.service.UpdateTransactionService;

import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;

import javax.ws.rs.core.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateCommnadController.class)
class UpdateCommnadControllerTest {

    @MockBean
    private UpdateTransactionService updateTransactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void newPhone() throws Exception{
        String productID="12345";
        String email="abc@gmail.com";
        Double bidAmount=12.00;
        //  TransactionUpdateEvent t1= new TransactionUpdateEvent();

        Mockito.when(updateTransactionService.update(productID,email,bidAmount)).thenReturn(TransactionUpdateEvent.builder().build());

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/e-auction/api/v1/buyer/update-bid/12345/abc@Gmail.com/12")

                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isAccepted());
    }
}