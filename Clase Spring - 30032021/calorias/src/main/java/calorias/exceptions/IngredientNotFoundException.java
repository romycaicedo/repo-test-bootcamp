package calorias.exceptions;


import org.springframework.http.HttpStatus;


public class IngredientNotFoundException extends Exception {

    public IngredientNotFoundException(String message,String name, HttpStatus status){
        super(message);
    }

}
