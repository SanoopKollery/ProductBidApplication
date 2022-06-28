package ta.transaction.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ta.transaction.command.domain.service.UpdateProductService;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class UpdateCommnadController {

    @Autowired
    private UpdateProductService updateProductService;


    @PutMapping("/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionUpdateEvent newPhone(@PathVariable String productId,
                                           @PathVariable String buyerEmailld,
                                           @PathVariable BigDecimal newBidAmount)  {
        return updateProductService.update(productId,buyerEmailld,newBidAmount);
    }
}
