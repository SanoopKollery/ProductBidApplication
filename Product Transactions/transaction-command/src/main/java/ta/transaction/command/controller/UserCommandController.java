package ta.transaction.command.controller;

import org.springframework.web.bind.annotation.*;
import ta.transaction.command.dto.CreateTransactionRequest;
import ta.transaction.command.domain.service.CreateTransactionService;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class UserCommandController {

    @Autowired
    private CreateTransactionService createTransactionService;

    @PostMapping("/place-bid")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionCreatedEvent newPhone(@RequestBody @Valid CreateTransactionRequest req) throws TransactionExistsException, ProductNotFound, URISyntaxException {
        System.out.println(req);
        return createTransactionService.create(req);
    }

}
