package ta.transaction.query.infrastructure.repository;

import ta.transaction.query.domain.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
    Optional<List<Transaction>> findByProductId(String productID);
    Transaction findByProductIdAndEmail(String productId,String emailID);
}
