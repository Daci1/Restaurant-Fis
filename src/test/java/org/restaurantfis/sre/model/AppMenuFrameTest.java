package org.restaurantfis.sre.model;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.services.UserService;
import org.restaurantfis.sre.services.UserServiceTest;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppMenuFrameTest {
    private FrameFixture window;

    @Test
    public void configButton(){
        AppMenuFrame frame = new AppMenuFrame();
        frame.dispose();
        JButton testButton = new JButton();
        frame.configButton(testButton, "testButtonText",100);

        Assertions.assertAll(
                () -> assertEquals("testButtonText", testButton.getText()),
                () -> assertEquals(100, testButton.getX()),
                () -> assertEquals(0, testButton.getY()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 15), testButton.getFont()),
                () -> assertEquals(new Dimension(100,50),testButton.getSize()),
                () -> assertEquals(false, testButton.isFocusable()),
                () -> assertEquals(true, testButton.getActionListeners().length > 0)
        );

    }

    @Test
    public void configTableButton(){
        AppMenuFrame frame = new AppMenuFrame();
        frame.dispose();
        JButton testButton = new JButton();
        frame.configTableButton(testButton, 100,150);

        Assertions.assertAll(
                () -> assertEquals(new Dimension(150,150), testButton.getSize()),
                () -> assertEquals(100, testButton.getX()),
                () -> assertEquals(150, testButton.getY()),
                () -> assertEquals(new Color(0,82,33), testButton.getBackground()),
                () -> assertEquals(false, testButton.isFocusable()),
                () -> assertEquals(true, testButton.getActionListeners().length > 0),
                () -> assertEquals(null, testButton.getBorder())
        );
    }

    @Test
    public void restructureAfterLogin(){
        UserService.initializeDB();
        UserServiceTest.addTestUser();
        UserService.validateLogin("testEmail", "testPass");

        AppMenuFrame frame = new AppMenuFrame();
        frame.restructureAfterLogin();

        Assertions.assertAll(
                () -> assertEquals(false, frame.registerButton.isVisible()),
                () -> assertEquals(false, frame.loginButton.isVisible()),
                () -> assertEquals(true, frame.logOutButton.isVisible()),

                () -> assertEquals("Menu", frame.menuButton.getText()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 15), frame.menuButton.getFont()),
                () -> assertEquals(new Dimension(100, 50), frame.menuButton.getSize()),
                () -> assertEquals(0, frame.menuButton.getX()),
                () -> assertEquals(0, frame.menuButton.getY()),
                () -> assertEquals(false, frame.menuButton.isFocusable()),

                () -> assertEquals("Contact", frame.contactButton.getText()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 15), frame.contactButton.getFont()),
                () -> assertEquals(new Dimension(100,50), frame.contactButton.getSize()),
                () -> assertEquals(100, frame.contactButton.getX()),
                () -> assertEquals(0, frame.contactButton.getY()),
                () -> assertEquals(false, frame.contactButton.isFocusable())
        );
        frame.dispose();
        UserServiceTest.removeTestUser();
    }


}
