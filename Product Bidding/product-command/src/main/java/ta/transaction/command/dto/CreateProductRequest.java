package ta.transaction.command.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class CreateProductRequest {

    private String productId;
    @NotNull(message="Product name should be mandatory!")
    @Size(min=5,max=30,message=" Product name length should be between 5 and 30 !")
    private String productName;
    private String shortDescription;
    private String description;
    private String category;
    private BigDecimal startingPrice;
    private String bidEndDate;

}
