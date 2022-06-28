package ta.transaction.query.infrastructure.repository;

import ta.transaction.query.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Optional<Product> findByProductId(String productID);

}
