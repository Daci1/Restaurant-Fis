package org.restaurantfis.sre;
import org.restaurantfis.sre.model.*;



import org.restaurantfis.sre.model.AppMenuFrame;
import org.restaurantfis.sre.services.UserService;

public class Main{

    public static void main(String args[])
    {

        UserService.initializeDB();
        //new AppMenuFrame();
       //new RegistrationFrame();
        //new LoginFrame();
        new clientContactinfo();

    }



}
