package ta.transaction.command.exception;

public class TransactionExistsException extends Exception {

    public TransactionExistsException(String str)
    {
        super(str);
    }
}
