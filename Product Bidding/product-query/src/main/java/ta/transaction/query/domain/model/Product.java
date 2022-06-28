package ta.transaction.query.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @JsonIgnore
    private String id;
    private String productId;
    private String productName;
    private String shortDescription;
    private String description;
    private String category;
    private BigDecimal startingPrice;
    private String bidEndDate;

}
