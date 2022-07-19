package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ta.transaction.command.domain.converter.TransactionConverter;
import ta.transaction.command.exception.TransactionNotFoundException;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionUpdatedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;
import ta.transaction.command.infrasturcture.repository.TransactionRepository;

import java.math.BigDecimal;

@Service
@Log4j2
public class UpdateTransactionService {

    Logger logger= LoggerFactory.getLogger(UpdateTransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private KafkaTransactionUpdatedEventSourcing kafkaTransactionUpdatedEventSourcing;

    public TransactionUpdateEvent update(String productID, String email, double bidAmount) throws TransactionNotFoundException {
        log.info("Updating Transaction-{},{}", productID, email);
        val transaction1=transactionRepository.findByProductIdAndEmail(productID, email);
        if (transaction1 == null || transaction1.getFirstName() == null)
            throw new TransactionNotFoundException("Transaction Not Found !!");
        transaction1.setBidAmount(bidAmount);
        val trx2=transactionConverter.createTransactionRequestWithID(transaction1);
        try
        {
            logger.info("Updating transaction with ID");
            transactionRepository.save(trx2);
            return kafkaTransactionUpdatedEventSourcing.publicUpdateTransactionEvent(trx2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
