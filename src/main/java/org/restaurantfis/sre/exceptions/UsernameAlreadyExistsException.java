package org.restaurantfis.sre.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    private String username;

    public UsernameAlreadyExistsException(String username) {
        super("An account with the username " +  username + " already exists!");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
