package ta.transaction.query.domain.service;

import lombok.val;
import org.springframework.web.client.RestTemplate;

import ta.transaction.query.domain.converter.TransactionConverter;
import ta.transaction.query.domain.exception.TransactionNotFoundException;
import ta.transaction.query.domain.model.Transaction;
import ta.transaction.query.domain.model.TransactionResponse;
import ta.transaction.query.infrastructure.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FindTransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse findByProductID(String productID) throws TransactionNotFoundException, URISyntaxException {
        URI uri = new URI("http://localhost:5001/e-auction/api/v1/seller/get-product/"+productID);
        TransactionResponse response = new TransactionResponse();
        try {
             response = restTemplate.getForObject(uri, TransactionResponse.class);
        }
        catch (Exception e)
        {
            throw new TransactionNotFoundException("Product Not Valid !","");
        }
        List<Transaction> transactions = transactionRepository.findByProductId(productID)
                .orElseThrow(() -> new TransactionNotFoundException(productID, "Product Transaction not found"));
        log.info("Find Transaction: {}", transactions);
        transactions = transactions.stream().sorted(Comparator.comparingDouble(Transaction::getBidAmount).reversed())
                .collect(Collectors.toList());
        response.setTransactions(transactions);
        return  response;
        //return transactionConverter.transactionResponse(transactions);
    }

    public void createTransaction(Transaction p) {
        log.info("Insert new transaction: {}", p);
        transactionRepository.save(p);
    }

    public void updateTransaction(Transaction p) {
        Transaction transaction = transactionRepository.findByProductIdAndEmail(p.getProductId(),p.getEmail());
        transaction.setBidAmount(p.getBidAmount());
        val trx2=transactionConverter.createTransactionRequestWithID(transaction);
        try {
            transactionRepository.save(trx2);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() );
        }
    }


}
