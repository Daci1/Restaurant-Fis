package org.restaurantfis.sre;


import com.mongodb.*;
import org.restaurantfis.sre.model.Date;
import org.restaurantfis.sre.model.RegistrationFrame;
import org.restaurantfis.sre.model.User;
import org.restaurantfis.sre.services.UserService;

public class Main{



    public static void main(String args[])
    {
        UserService.initializeDB();
        UserService.dropDB();
//        UserService.addUser("admin", "admin@ceva", "adminpass", "07", "SuperMegaStraight", new Date(1,2,3), "Acasa");
//        try
//        {
//            UserService.checkExistingUser("admin");
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }

        new RegistrationFrame();

        UserService.printCollection();

    }



}


