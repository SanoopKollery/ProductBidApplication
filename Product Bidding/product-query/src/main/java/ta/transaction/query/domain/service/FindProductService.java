package ta.transaction.query.domain.service;

import ta.transaction.query.domain.converter.ProductConverter;
import ta.transaction.query.domain.exception.ProductNotFoundException;
import ta.transaction.query.domain.model.Product;
import ta.transaction.query.infrastructure.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class FindProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    public Product findByProductID(String productID) throws ProductNotFoundException {
        Product products = productRepository.findByProductId(productID)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found !",""));
        if (products == null || products.getProductId() == null ||
             products.getProductId().length() <= 0)
            throw new ProductNotFoundException("Product not found !!","");
        log.info("Find Transaction: {}", products);
        return products;
        //return transactionConverter.transactionResponse(transactions);
    }

    public void createProduct(Product p) {
        log.info("Insert new Product: {}", p);
        productRepository.save(p);
    }

    public void deleteProduct(Product p) {
        Optional<Product> product = productRepository.findByProductId(p.getProductId());
        log.info("Delete Product : {}", p);
        productRepository.delete(product.get());
    }

    public void updateProduct(Product p) {
       /* Product product = transactionRepository.findByProductIdAndEmail(p.getProductId(),p.getEmail());
        product.setBidAmount(p.getBidAmount());
        val trx2= productConverter.createTransactionRequestWithID(product);
        try {
            transactionRepository.save(trx2);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() );
        }*/
    }


    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public List<Product> findByProductName(String productName) {
        if (productName == null)
            return productRepository.findAll();
        List<Product> products = productRepository.findByProductNameIsLike(productName);
        return products;
    }
}
