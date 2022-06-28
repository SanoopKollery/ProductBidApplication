package ta.transaction.command.infrasturcture.repository;

import ta.transaction.command.domain.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
Transaction findByProductIdAndEmail(String productID, String emailID);
Transaction findByProductId(String productID);

}
