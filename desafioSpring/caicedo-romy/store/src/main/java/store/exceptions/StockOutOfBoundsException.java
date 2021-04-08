package store.exceptions;

public class StockOutOfBoundsException extends Exception {
    public StockOutOfBoundsException(String message){
        super(message);
    }
}
