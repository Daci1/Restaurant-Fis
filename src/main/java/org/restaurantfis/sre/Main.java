package org.restaurantfis.sre;


import org.restaurantfis.sre.model.LoginFrame;
import org.restaurantfis.sre.services.UserService;

public class Main{
    public static void main(String args[])
    {
        UserService.initializeDB();
//        UserService.dropDB();

        new LoginFrame();

//        UserService.printCollection();
    }

}


