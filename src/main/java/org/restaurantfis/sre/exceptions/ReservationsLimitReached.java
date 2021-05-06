package org.restaurantfis.sre.exceptions;

public class ReservationsLimitReached extends Exception{

    public ReservationsLimitReached(){
        super("Only one reservation per table is possible!");
    }

    public String toString(){
        return "Only one reservation per table is possible!";
    }
}
