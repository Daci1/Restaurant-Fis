package org.restaurantfis.sre.model;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

import java.text.DateFormatSymbols;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationlistFrameTest {


    @Test
    public void addRow(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        ReservationlistFrame frame = new ReservationlistFrame();

        frame.addRow("testName", "testTable", "testHour", 1 ,2);

        Assertions.assertAll(
                () -> assertEquals("testName", frame.menu.getValueAt(frame.menu.getRowCount() -1 ,0)),
                () -> assertEquals("testTable", frame.menu.getValueAt(frame.menu.getRowCount() -1 ,1)),
                () -> assertEquals("testHour", frame.menu.getValueAt(frame.menu.getRowCount() -1 ,2)),
                () -> assertEquals(1, frame.menu.getValueAt(frame.menu.getRowCount() -1 ,3)),
                () -> assertEquals(new DateFormatSymbols().getMonths()[2-1], frame.menu.getValueAt(frame.menu.getRowCount() -1 ,4))

        );

        frame.dispose();
    }
    @Test
    public void populateReservationList(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        ReservationlistFrame frame = new ReservationlistFrame();
        frame.dispose();


        frame.populateReservationList();

        DBCursor cursor = ReservationService.getTablesCollection().find();
        int i = 0;
        while(cursor.hasNext())
        {
            DBObject currentDBObject = cursor.next();

            assertEquals((String)currentDBObject.get("userName"), frame.menu.getValueAt(i, 0));
            assertEquals((String)currentDBObject.get("tableName"), frame.menu.getValueAt(i, 1));
            assertEquals((String)currentDBObject.get("reservationHour"), frame.menu.getValueAt(i, 2));
            assertEquals((int)currentDBObject.get("reservationDay"), frame.menu.getValueAt(i, 3));
            assertEquals(new DateFormatSymbols().getMonths()[(int)currentDBObject.get("reservationMonth") - 1], frame.menu.getValueAt(i, 4));
            i++;
        }



    }

}
