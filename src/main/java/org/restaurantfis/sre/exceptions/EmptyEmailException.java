package org.restaurantfis.sre.exceptions;

public class EmptyEmailException extends Exception{
    public EmptyEmailException()
    {
        super("The email cannot be empty.");
    }
}
