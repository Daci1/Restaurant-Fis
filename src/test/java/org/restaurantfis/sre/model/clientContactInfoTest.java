package org.restaurantfis.sre.model;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

import java.text.DateFormatSymbols;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class clientContactInfoTest {

    @Test
    public void addRow(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        clientContactinfo frame = new clientContactinfo();

        frame.addRow("testName", "testEmail", "testAddress");

        Assertions.assertAll(
                () -> assertEquals("testName", frame.clients.getValueAt(frame.clients.getRowCount() -1 ,0)),
                () -> assertEquals("testEmail", frame.clients.getValueAt(frame.clients.getRowCount() -1 ,1)),
                () -> assertEquals("testAddress", frame.clients.getValueAt(frame.clients.getRowCount() -1 ,2))
        );

        frame.dispose();
    }

    @Test
    public void addTotable(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        clientContactinfo frame = new clientContactinfo();
        frame.dispose();

        frame.addTotable();

        DBCursor cursor = UserService.getUsersCollection().find();
        int i = 0;
        while(cursor.hasNext())
        {
            DBObject currentDBObject = cursor.next();

            assertEquals((String)currentDBObject.get("name"), frame.clients.getValueAt(i, 0));
            assertEquals((String)currentDBObject.get("email"), frame.clients.getValueAt(i, 1));
            assertEquals((String)currentDBObject.get("address"), frame.clients.getValueAt(i, 2));
            i++;
        }

        frame.addTotable();

    }
    
}
