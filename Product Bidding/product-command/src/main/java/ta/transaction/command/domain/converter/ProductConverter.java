package ta.transaction.command.domain.converter;

import ta.transaction.command.application.dto.CreateProductRequest;
import ta.transaction.command.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product createProductRequest(CreateProductRequest command) {
        return Product.builder()
                .productId(command.getProductId())
                .productName(command.getProductName())
                .shortDescription(command.getShortDescription())
                .description(command.getDescription())
                .category(command.getCategory())
                .startingPrice(command.getStartingPrice())
                .bidEndDate(command.getBidEndDate())
                .build();
    }

    public Product createTransactionRequestWithID(Product command) {
        return Product.builder()
                .id(command.getId())
                .productId(command.getProductId())
                .productName(command.getProductName())
                .shortDescription(command.getShortDescription())
                .description(command.getDescription())
                .category(command.getCategory())
                .startingPrice(command.getStartingPrice())
                .bidEndDate(command.getBidEndDate())
                .build();
    }
}
