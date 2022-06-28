package ta.transaction.command.infrasturcture.eventsourcing.events;

import lombok.Builder;
import lombok.Data;
import ta.transaction.command.domain.model.Transaction;

@Builder
@Data
public class TransactionUpdateEvent {
    private Transaction transaction;
}
