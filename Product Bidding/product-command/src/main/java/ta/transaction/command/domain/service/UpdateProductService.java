package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.domain.converter.ProductConverter;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionUpdatedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;
import ta.transaction.command.infrasturcture.repository.ProductRepository;

import java.math.BigDecimal;

@Service
@Log4j2
public class UpdateProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private KafkaTransactionUpdatedEventSourcing kafkaTransactionUpdatedEventSourcing;

    public TransactionUpdateEvent update(String productID, String email, BigDecimal bidAmount)  {
      /*  log.info("Updating Transaction-{},{}", productID, email);
        val transaction1= productRepository.findByProductIdAndEmail(productID, email);
        if (transaction1 == null || transaction1.getFirstName() == null)
            throw new TransactionNotFoundException("Transaction Not Found !!");
        transaction1.setBidAmount(bidAmount);
        val trx2= productConverter.createTransactionRequestWithID(transaction1);
        try
        {
            productRepository.save(trx2);
            return kafkaTransactionUpdatedEventSourcing.publicUpdateTransactionEvent(trx2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }*/
        return null;
    }

}
