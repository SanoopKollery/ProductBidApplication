package ta.transaction.command.infrasturcture.eventsourcing.events;

import ta.transaction.command.domain.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TransactionCreatedEvent {
    private UUID uuid;
    private Transaction transaction;
}
