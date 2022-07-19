package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.controller.DeleteCommandController;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionDeletedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductDeleteEvent;
import ta.transaction.command.infrasturcture.repository.ProductRepository;

@Service
@Log4j2
public class DeleteProductService {

    Logger logger= LoggerFactory.getLogger(DeleteProductService.class);

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
        logger.info("Deleting Product");
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
