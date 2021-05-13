package org.restaurantfis.sre.services;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {

    @Test
    public void testInitializeDB(){
        ReservationService.initializeDB();
        DBCollection tablesCollection = ReservationService.getTablesCollection();
        DB database = tablesCollection.getDB();

        Assertions.assertAll(   () -> assertEquals("tablesCollection",tablesCollection.getName()),
                () ->  assertEquals("TablesDB", database.getName()));

    }

}
