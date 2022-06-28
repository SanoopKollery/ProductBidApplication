package ta.transaction.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.application.dto.CreateTransactionRequest;
import ta.transaction.command.domain.service.CreateTransactionService;
import ta.transaction.command.domain.service.UpdateTransactionService;
import ta.transaction.command.exception.TransactionNotFoundException;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionCreatedEvent;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class UpdateCommnadController {

    @Autowired
    private UpdateTransactionService updateTransactionService;


    @PutMapping("/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionUpdateEvent newPhone(@PathVariable String productId,
                                           @PathVariable String buyerEmailld,
                                           @PathVariable double newBidAmount) throws TransactionNotFoundException {
        return updateTransactionService.update(productId,buyerEmailld,newBidAmount);
    }
}
