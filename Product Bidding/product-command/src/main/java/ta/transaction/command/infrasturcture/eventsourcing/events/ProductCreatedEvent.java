package ta.transaction.command.infrasturcture.eventsourcing.events;

import ta.transaction.command.domain.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ProductCreatedEvent {
    private Product product;
    private boolean saved;
}
