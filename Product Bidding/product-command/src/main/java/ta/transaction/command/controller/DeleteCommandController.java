package ta.transaction.command.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ta.transaction.command.domain.service.DeleteProductService;
import ta.transaction.command.exception.FutureDateException;
import ta.transaction.command.exception.ProductCategoryException;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductDeleteEvent;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/e-auction/api/v1/seller")
public class DeleteCommandController {



    @Autowired
    private DeleteProductService deleteProductService;

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDeleteEvent createProduct(@PathVariable  String productId) throws TransactionExistsException, FutureDateException, ProductCategoryException, ProductNotFound {

        return deleteProductService.delete(productId);

    }
}
