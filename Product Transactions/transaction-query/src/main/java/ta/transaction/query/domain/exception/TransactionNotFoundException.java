package ta.transaction.query.domain.exception;

public class TransactionNotFoundException extends Exception{
    private String message;
    private String name;

    public TransactionNotFoundException(String name, String message) {
        super(message);
        this.name = name;
    }
}
