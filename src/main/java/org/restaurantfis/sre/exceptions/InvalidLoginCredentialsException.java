package org.restaurantfis.sre.exceptions;

public class InvalidLoginCredentialsException extends Exception{
    public InvalidLoginCredentialsException(){
        super("Login credentials are invalid.");
    }
}
