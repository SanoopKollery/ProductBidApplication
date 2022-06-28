package ta.transaction.query.domain.exception;

public class ProductNotFoundException extends Exception{
    private String message;
    private String name;

    public ProductNotFoundException(String name, String message) {
        super(message);
        this.name = name;
    }
}
