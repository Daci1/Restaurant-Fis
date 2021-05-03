package org.restaurantfis.sre;



import org.restaurantfis.sre.model.*;
import com.mongodb.*;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

public class Main{



    public static void main(String args[])
    {
        UserService.initializeDB();
        new AppMenuFrame();
    }



}


