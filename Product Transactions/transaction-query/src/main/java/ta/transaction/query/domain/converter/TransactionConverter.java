package ta.transaction.query.domain.converter;

import ta.transaction.query.application.dto.TransactionResponse;
import ta.transaction.query.domain.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public TransactionResponse transactionResponse(Transaction trxn) {
        return TransactionResponse.builder()
                .firstName(trxn.getFirstName())
                .lastName(trxn.getLastName())
                .address(trxn.getAddress())
                .city(trxn.getCity())
                .state(trxn.getState())
                .pin(trxn.getPin())
                .phone(trxn.getPhone())
                .email(trxn.getEmail())
                .productId(trxn.getProductId())
                .bidAmount(trxn.getBidAmount())
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
