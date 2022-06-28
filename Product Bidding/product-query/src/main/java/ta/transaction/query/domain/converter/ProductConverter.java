package ta.transaction.query.domain.converter;

import ta.transaction.query.application.dto.ProductResponse;
import ta.transaction.query.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductResponse transactionResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .shortDescription(product.getShortDescription())
                .description(product.getDescription())
                .category(product.getCategory())
                .startingPrice(product.getStartingPrice())
                .bidEndDate(product.getBidEndDate())
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
