package org.restaurantfis.sre.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.exceptions.RegistrationEmptyTextboxException;
import org.restaurantfis.sre.exceptions.WrongAdminPasswordException;
import org.restaurantfis.sre.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFrameTest {

    @Test
    public void RegistrationEmptyTextBoxException(){
        RegistrationFrame frame = new RegistrationFrame();
        frame.dispose();
        Assertions.assertThrows(RegistrationEmptyTextboxException.class, () -> frame.checkEmptyTextboxes());
    }

    @Test
    public void WrongAdminPasswordException(){
        RegistrationFrame frame = new RegistrationFrame();
        frame.dispose();

        frame.tadmin.setText("testAdminPass");


        Assertions.assertThrows(WrongAdminPasswordException.class, () -> {
            if(!new String (frame.tadmin.getPassword()).equals(frame.tpass)) throw new WrongAdminPasswordException();
        });

    }

    @Test
    public void testResetButton(){
        RegistrationFrame frame = new RegistrationFrame();
        frame.dispose();

        frame.tname.setText("testName");
        frame.temail.setText("testEmail");
        frame.tpass.setText("testPassword");
        frame.tmno.setText("testMobile");
        frame.tadd.setText("testAddress");
        frame.date.setSelectedIndex(3);
        frame.month.setSelectedIndex(2);
        frame.year.setSelectedIndex(6);
        frame.adminCheck.setSelected(true);
        frame.tadmin.setText("testAdminPass");

        frame.reset.doClick();

        Assertions.assertAll(
                () -> assertEquals("", frame.tname.getText()),
                () -> assertEquals("", frame.temail.getText()),
                () -> assertEquals("", new String(frame.tpass.getPassword())),
                () -> assertEquals("", frame.tmno.getText()),
                () -> assertEquals("", frame.tadd.getText()),
                () -> assertEquals(0, frame.date.getSelectedIndex()),
                () -> assertEquals(0, frame.month.getSelectedIndex()),
                () -> assertEquals(0, frame.year.getSelectedIndex()),
                () -> assertEquals(false, frame.adminCheck.isSelected()),
                () -> assertEquals("", new String(frame.tadmin.getPassword())),
                () -> assertEquals("", frame.res.getText())
                );
    }

    @Test
    public void testAddedAccount(){
        RegistrationFrame frame = new RegistrationFrame();
        frame.dispose();
        UserService.initializeDB();

        frame.tname.setText("testName");
        frame.temail.setText("testEmail");
        frame.tpass.setText("testPassword");
        frame.tmno.setText("testMobile");
        frame.tadd.setText("testAddress");
        frame.date.setSelectedIndex(3);
        frame.month.setSelectedIndex(2);
        frame.year.setSelectedIndex(6);

        frame.sub.doClick();

        BasicDBObject query = new BasicDBObject();
        query.put("name", "testName");
        query.put("email", "testEmail");
        query.put("mobile", "testMobile");
        query.put("address", "testAddress");
        query.put("isAdmin", false);


        DBCursor cursor = UserService.getUsersCollection().find(query);

        Assertions.assertAll(
                () -> assertEquals("testName", (String) cursor.one().get("name")),
                () -> assertEquals("testEmail", (String) cursor.one().get("email")),
                () -> assertEquals(UserService.encodePassword("testPassword"), (String) cursor.one().get("password")),
                () -> assertEquals("testMobile", (String) cursor.one().get("mobile")),
                () -> assertEquals("testAddress", (String) cursor.one().get("address")),
                () -> assertEquals(false, (boolean) cursor.one().get("isAdmin")),
                () -> assertEquals("Male", (String) cursor.one().get("gender"))
                );

        UserService.getUsersCollection().remove(cursor.one());


    }

}
