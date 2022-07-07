package ta.transaction.command.application.controller;

import org.springframework.web.bind.annotation.*;
import ta.transaction.command.application.dto.CreateProductRequest;
import ta.transaction.command.domain.service.CreateProductService;
import ta.transaction.command.exception.FutureDateException;
import ta.transaction.command.exception.ProductCategoryException;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/e-auction/api/v1/seller")
public class UserCommandController {

    @Autowired
    private CreateProductService createProductService;

    @PostMapping("/add-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreatedEvent createProduct(@RequestBody @Valid CreateProductRequest req) throws TransactionExistsException, FutureDateException, ProductCategoryException {
        return createProductService.create(req);
    }

}
