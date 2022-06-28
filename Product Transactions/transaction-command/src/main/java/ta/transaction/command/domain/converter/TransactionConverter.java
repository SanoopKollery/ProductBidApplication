package ta.transaction.command.domain.converter;

import ta.transaction.command.application.dto.CreateTransactionRequest;
import ta.transaction.command.domain.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public Transaction createTransactionRequest(CreateTransactionRequest command) {
        return Transaction.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(command.getAddress())
                .city(command.getCity())
                .state(command.getState())
                .pin(command.getPin())
                .phone(command.getPhone())
                .email(command.getEmail())
                .productId(command.getProductId())
                .bidAmount(command.getBidAmount())
                .build();
    }

    public Transaction createTransactionRequestWithID(Transaction command) {
        return Transaction.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(command.getAddress())
                .city(command.getCity())
                .state(command.getState())
                .pin(command.getPin())
                .phone(command.getPhone())
                .email(command.getEmail())
                .productId(command.getProductId())
                .bidAmount(command.getBidAmount())
                .build();
    }
}
