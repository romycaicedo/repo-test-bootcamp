package store.exceptions;

public class ArticleNotFoundException extends Exception{
    public ArticleNotFoundException(String message){
        super(message);
    }
}
