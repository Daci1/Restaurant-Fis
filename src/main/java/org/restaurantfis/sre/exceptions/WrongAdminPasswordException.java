package org.restaurantfis.sre.exceptions;

public class WrongAdminPasswordException extends Exception{
    public WrongAdminPasswordException(){
        super("Wrong admin password entered");
    }
}
