package ta.transaction.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ta.transaction.command.application.dto.CreateProductRequest;
import ta.transaction.command.domain.converter.ProductConverter;
import ta.transaction.command.domain.model.ProductCategory;
import ta.transaction.command.exception.FutureDateException;
import ta.transaction.command.exception.ProductCategoryException;
import ta.transaction.command.exception.TransactionExistsException;
import ta.transaction.command.infrasturcture.eventsourcing.KafkaTransactionCreatedEventSourcing;
import ta.transaction.command.infrasturcture.eventsourcing.events.ProductCreatedEvent;
import ta.transaction.command.infrasturcture.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@Log4j2
public class CreateProductService {

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTransactionCreatedEventSourcing kafkaProductCreatedEventSourcing;

    public ProductCreatedEvent create(CreateProductRequest request) throws TransactionExistsException, FutureDateException, ProductCategoryException {
        if (request.getBidEndDate().length() == 10)
            request.setBidEndDate(String.format("%s 00:00:00",request.getBidEndDate()));
        boolean futureOrNot = getFutureOrNot(Timestamp.valueOf(request.getBidEndDate()));
        if (!futureOrNot)
            throw new FutureDateException("Bid end date should be future !!");
        ProductCategory cat = ProductCategory.valueOf(request.getCategory());
        if (cat == null || cat.getDisplayName() == null)
            throw new ProductCategoryException("Product Category Not Valid !");
        log.info("Creating Product");
        val product = productConverter.createProductRequest(request);
        val id = UUID.randomUUID();
        product.setProductId(String.valueOf(id));
        productRepository.save(product);
        try {
            return kafkaProductCreatedEventSourcing.publicCreateProductEvent(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean getFutureOrNot(Timestamp ts)
    {
        return (ts.after(new Timestamp(System.currentTimeMillis())));
    }
}
