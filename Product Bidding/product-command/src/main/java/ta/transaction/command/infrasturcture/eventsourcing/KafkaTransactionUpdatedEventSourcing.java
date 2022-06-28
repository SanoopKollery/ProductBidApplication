package ta.transaction.command.infrasturcture.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ta.transaction.command.domain.model.Product;
import ta.transaction.command.infrasturcture.eventsourcing.events.TransactionUpdateEvent;

@Component
@Log4j2
public class KafkaTransactionUpdatedEventSourcing {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.updateProduct}")
    private String topicName;

    public TransactionUpdateEvent publicUpdateTransactionEvent(Product product) throws JsonProcessingException {
        ObjectWriter objectWriter= new ObjectMapper().writer().withDefaultPrettyPrinter();
        val  json = objectWriter.writeValueAsString(product);
        log.info("Send json '{}' to topic {}", json, topicName);
        kafkaTemplate.send(topicName, json);
        return TransactionUpdateEvent.builder().product(product).build();
    }
}
