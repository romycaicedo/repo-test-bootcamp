package tracker.tracker.exceptions;

import org.springframework.http.HttpStatus;

public class LinkNotFoundException extends Exception{
    public LinkNotFoundException(String message){
        super(message);
    }


}
