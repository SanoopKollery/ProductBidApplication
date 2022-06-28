package ta.transaction.command.infrasturcture.eventsourcing.events;

import lombok.Builder;
import lombok.Data;
import ta.transaction.command.domain.model.Product;

@Builder
@Data
public class TransactionUpdateEvent {
    private Product product;
}
