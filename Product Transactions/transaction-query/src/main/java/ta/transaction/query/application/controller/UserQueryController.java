package ta.transaction.query.application.controller;

import org.springframework.web.bind.annotation.*;
import ta.transaction.query.application.exception.FindTransactionException;
import ta.transaction.query.domain.exception.TransactionNotFoundException;
import ta.transaction.query.domain.model.Transaction;
import ta.transaction.query.domain.model.TransactionResponse;
import ta.transaction.query.domain.service.FindTransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/e-auction/api/v1/seller/")
public class UserQueryController {

    @Autowired
    private FindTransactionService findTransactionService;

    @GetMapping("show-bids/{productID}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse findTransaction(@PathVariable String productID) {
        try{
            return findTransactionService.findByProductID(productID);
        }catch (TransactionNotFoundException | URISyntaxException ex) {
            log.error(ex);
            throw new FindTransactionException();
        }
    }
}
