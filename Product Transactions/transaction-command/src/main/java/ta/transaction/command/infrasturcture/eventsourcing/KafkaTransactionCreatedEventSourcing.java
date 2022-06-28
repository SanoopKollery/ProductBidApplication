package ta.transaction.command.infrasturcture.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ta.transaction.command.domain.model.Transaction;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionCreatedEvent;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class KafkaTransactionCreatedEventSourcing {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.createTransaction}")
    private String topicName;

    public TransactionCreatedEvent publicCreateTransactionEvent(Transaction transaction) throws JsonProcessingException {
        val id = UUID.randomUUID();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        val json = objectWriter.writeValueAsString(transaction);
        log.info("Send json '{}' to topic {}", json, topicName);
        kafkaTemplate.send(topicName, json);
        return TransactionCreatedEvent.builder().uuid(id).transaction(transaction).build();
    }

}
