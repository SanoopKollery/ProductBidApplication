package ta.transaction.query.application.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ta.transaction.query.application.exception.FindTransactionException;
import ta.transaction.query.domain.exception.ProductNotFoundException;
import ta.transaction.query.domain.model.Product;
import ta.transaction.query.domain.service.FindProductService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Log4j2
@RequestMapping("/e-auction/api/v1/seller")
public class ProductListController {

    @Autowired
    private FindProductService findProductService;

    @GetMapping("/get-product")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProduct() {
        try{
            return findProductService.getProducts();
        }catch (Exception ex) {
            log.error(ex);
            throw new FindTransactionException();
        }
    }
}
