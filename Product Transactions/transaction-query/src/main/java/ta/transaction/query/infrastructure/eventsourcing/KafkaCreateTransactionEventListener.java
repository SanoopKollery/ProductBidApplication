package ta.transaction.query.infrastructure.eventsourcing;

import com.google.gson.Gson;
import ta.transaction.query.domain.model.Transaction;
import ta.transaction.query.domain.service.FindTransactionService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Log4j2
@Component
public class KafkaCreateTransactionEventListener {

    @Autowired
    private FindTransactionService findTransactionService;

    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${message.topic.createTransaction}")
    public void listen(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {
        Transaction trxn= new Gson().fromJson(stringStringConsumerRecord.value(), Transaction.class);
        findTransactionService.createTransaction(trxn);
        log.info("Insert trxn {} in reader database", trxn);
        latch.countDown();
    }
}
