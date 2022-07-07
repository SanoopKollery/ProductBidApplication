package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.application.dto.CreateProductRequest;
import ta.transaction.command.domain.model.ProductCategory;
import ta.transaction.command.domain.model.Transaction;
import ta.transaction.command.domain.model.TransactionResponse;
import ta.transaction.command.exception.FutureDateException;
import ta.transaction.command.exception.ProductCategoryException;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionCreatedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionDeletedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductCreatedEvent;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductDeleteEvent;
import ta.transaction.command.infrasturcture.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class DeleteProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTransactionDeletedEventSourcing kafkaProductDeletedEventSourcing;

    public ProductDeleteEvent delete(String productID) throws ProductNotFound, TransactionExistsException {
        String transactionResponse = "";
        try {
            transactionResponse = restTemplate.getForEntity("http://localhost:8082/tq/e-auction/api/v1/seller/show-bids/" + productID,
                   String.class).getBody();
       }
       catch (Exception e)
       {
           throw new ProductNotFound("Product Not Valid !!");
       }
       if (transactionResponse != null && transactionResponse.contains("firstName") )
           throw new TransactionExistsException("Bid Entries Exists !!");
        log.info("Deleting Product");
        val product = productRepository.findByProductId(productID);
       if (product == null || product.getProductId() == null
               || product.getProductId().length() <=0)
       {
           throw new ProductNotFound("Product not found !!");
       }
        try{
        productRepository.delete(product);
            return kafkaProductDeletedEventSourcing.publicDeleteProductEvent(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
