package org.restaurantfis.sre;



import org.restaurantfis.sre.model.RegistrationFrame;
import org.restaurantfis.sre.services.UserService;

public class Main{



    public static void main(String args[])
    {
        UserService.initializeDB();
        new RegistrationFrame();
    }



}


