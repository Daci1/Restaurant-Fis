package org.restaurantfis.sre.model;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.exceptions.EmptyEmailException;
import org.restaurantfis.sre.exceptions.EmptyPasswordException;
import org.restaurantfis.sre.exceptions.InvalidLoginCredentialsException;
import org.restaurantfis.sre.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginFrameTest {

    @Test
    public void EmptyEmailException(){
        LoginFrame frame = new LoginFrame();
        frame.dispose();
        Assertions.assertThrows(EmptyEmailException.class, () -> LoginFrame.checkCredentials());
        LoginFrame.temail.setText("");
    }

    @Test
    public void EmptyPasswordException(){
        LoginFrame frame = new LoginFrame();
        frame.dispose();
        LoginFrame.temail.setText("testEmail");
        Assertions.assertThrows(EmptyPasswordException.class, () -> LoginFrame.checkCredentials());
        LoginFrame.tpass.setText("");
    }

    @Test
    public void InvalidLoginCredentialsException(){
        LoginFrame frame = new LoginFrame();
        frame.dispose();
        LoginFrame.temail.setText("testEmail");
        LoginFrame.tpass.setText("testPassword");
        Assertions.assertThrows(InvalidLoginCredentialsException.class, () -> LoginFrame.checkCredentials());
        LoginFrame.temail.setText("");
        LoginFrame.tpass.setText("");
    }

    @Test
    public void testLoginUser(){
        LoginFrame frame = new LoginFrame();
        AppMenuFrame frame2 = new AppMenuFrame();
        frame.dispose();
        frame2.dispose();
        frame.temail.setText("daci@");
        frame.tpass.setText("123");
        frame.log_in.doClick();

        Assertions.assertAll(
                () -> assertEquals(true, UserService.isLogged()),
                () -> assertEquals(true, UserService.loggedUser != null)
        );
    }

}
