package org.restaurantfis.sre.exceptions;

public class RegistrationEmptyTextboxException extends Exception{
    public RegistrationEmptyTextboxException(){
        super("Textboxes cannot be empty");
    }
}
