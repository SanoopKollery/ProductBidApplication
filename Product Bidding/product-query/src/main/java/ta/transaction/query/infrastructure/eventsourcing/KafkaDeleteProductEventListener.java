package ta.transaction.query.infrastructure.eventsourcing;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ta.transaction.query.domain.model.Product;
import ta.transaction.query.domain.service.FindProductService;

import java.util.concurrent.CountDownLatch;

@Log4j2
@Component
public class KafkaDeleteProductEventListener {

    @Autowired
    private FindProductService findProductService;

    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${message.topic.deleteProduct}")
    public void listen(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {
        Product product= new Gson().fromJson(stringStringConsumerRecord.value(), Product.class);
        findProductService.deleteProduct(product);
        log.info("Delete product {} in reader database", product);
        latch.countDown();
    }
}
