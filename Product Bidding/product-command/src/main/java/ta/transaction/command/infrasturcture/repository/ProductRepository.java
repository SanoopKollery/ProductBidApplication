package ta.transaction.command.infrasturcture.repository;

import ta.transaction.command.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product findByProductId(String productID);
}
