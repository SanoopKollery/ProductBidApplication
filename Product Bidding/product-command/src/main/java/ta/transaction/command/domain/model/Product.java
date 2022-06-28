package ta.transaction.command.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
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
