package org.restaurantfis.sre.exceptions;

public class EmptyPasswordException extends Exception{
    public EmptyPasswordException(){
        super("The password cannot be empty.");
    }
}
