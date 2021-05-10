package org.restaurantfis.sre;
import org.restaurantfis.sre.model.LoginFrame;
import com.mongodb.*;
import org.restaurantfis.sre.model.Date;
import org.restaurantfis.sre.model.RegistrationFrame;
import org.restaurantfis.sre.model.User;
import org.restaurantfis.sre.services.UserService;

import org.restaurantfis.sre.model.GUImenu;

public class Main{



    public static void main(String args[])
    {

        UserService.initializeDB();
        new LoginFrame();

    }



}
