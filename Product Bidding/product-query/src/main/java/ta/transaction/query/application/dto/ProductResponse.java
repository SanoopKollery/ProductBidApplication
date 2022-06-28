package ta.transaction.query.application.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class ProductResponse {

    private String productId;
    private String productName;
    private String shortDescription;
    private String description;
    private String category;
    private BigDecimal startingPrice;
    private String bidEndDate;
}
