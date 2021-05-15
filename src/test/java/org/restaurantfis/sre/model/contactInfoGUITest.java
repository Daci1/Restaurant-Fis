package org.restaurantfis.sre.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class contactInfoGUITest {

    @Test
    public void checkCorrectInformation(){
        contactInfoGUI frame = new contactInfoGUI();
        frame.dispose();
        Assertions.assertAll(
                () -> assertEquals("Contact Information", frame.contact_info_title.getText()),
                () -> assertEquals("Address : Aleea Studentilor, nr 12, 300551", frame.adress.getText()),
                () -> assertEquals("Phone : 0713457930 / 0748914527 ", frame.phone_number.getText()),
                () -> assertEquals("Our official instagram page : restaurant.fis", frame.social_media.getText()),
                () -> assertEquals("1.Barbu Dacian - Manager", frame.staff_list[0]),
                () -> assertEquals("2.Popescu Ion - Chelner", frame.staff_list[1]),
                () -> assertEquals("3.Andrei Mihai - Bucatar", frame.staff_list[2]),
                () -> assertEquals("4.Belea Radu - Ajutor Bucatar", frame.staff_list[3]),
                () -> assertEquals("5.Becali Cristina - Chelnarita", frame.staff_list[4]),
                () -> assertEquals("6.Dragomir Andrei - Bucatar", frame.staff_list[5]),
                () -> assertEquals("STAFF", frame.restaurant_staff.getText())
                );
    }
}
