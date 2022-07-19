package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.dto.CreateTransactionRequest;
import ta.transaction.command.domain.converter.TransactionConverter;
import ta.transaction.command.domain.model.Product;
import ta.transaction.command.exception.ProductNotFound;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionCreatedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionCreatedEvent;
import ta.transaction.command.infrasturcture.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@Log4j2
public class CreateTransactionService {
    Logger logger= LoggerFactory.getLogger(CreateTransactionService.class);

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private KafkaTransactionCreatedEventSourcing kafkaTransactionCreatedEventSourcing;

    public TransactionCreatedEvent create(CreateTransactionRequest request) throws TransactionExistsException, ProductNotFound, URISyntaxException {
        val trxn = transactionRepository.findByProductIdAndEmail(request.getProductId(),request.getEmail());
       if (trxn != null && trxn.getFirstName().length() > 3)
        {
            throw new TransactionExistsException("Bid Already entered !!!");
        }
        URI uri = new URI("http://localhost:5001/e-auction/api/v1/seller/get-product/"+request.getProductId());
        try {

            Product product = restTemplate.getForObject(uri, Product.class);
            logger.info("BID Created Successfully");
        }
        catch (Exception e)
        {
            throw new ProductNotFound("Product Not Valid !");
        }
        log.info("Creating Transaction");
        val transaction = transactionConverter.createTransactionRequest(request);
        transactionRepository.save(transaction);
        try {
            return kafkaTransactionCreatedEventSourcing.publicCreateTransactionEvent(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
