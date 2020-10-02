package me.anant.PMS.exceptions;

public class ProductNotFoundException extends Exception {

    private String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
    }

}
