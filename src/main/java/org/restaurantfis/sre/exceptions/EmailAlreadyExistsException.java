package org.restaurantfis.sre.exceptions;

public class EmailAlreadyExistsException extends Exception{

    private String email;

    public EmailAlreadyExistsException(String email){
        super("An account with the username " +  email + " already exists!");
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}
