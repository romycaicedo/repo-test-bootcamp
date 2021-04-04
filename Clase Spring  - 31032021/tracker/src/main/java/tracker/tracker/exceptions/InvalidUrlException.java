package tracker.tracker.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUrlException extends Exception{
    public InvalidUrlException(String message){
        super(message);
    }


}
