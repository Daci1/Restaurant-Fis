package org.restaurantfis.sre.model;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppMenuFrameTest {
    private FrameFixture window;

//    @Before
//    public void setUp() {
//        AppMenuFrame frame = GuiActionRunner.execute(() -> new AppMenuFrame());
//        window = new FrameFixture(frame);
//        window.show(); // shows the frame to test
//    }


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
        AppMenuFrame frame = new AppMenuFrame();
        frame.dispose();
        AppMenuFrame.restructureAfterLogin();

        Assertions.assertAll(
                () -> assertEquals(false, AppMenuFrame.registerButton.isVisible()),
                () -> assertEquals(false, AppMenuFrame.loginButton.isVisible()),
                () -> assertEquals(true, AppMenuFrame.logOutButton.isVisible()),

                () -> assertEquals("Menu", AppMenuFrame.menuButton.getText()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 15), AppMenuFrame.menuButton.getFont()),
                () -> assertEquals(new Dimension(100, 50), AppMenuFrame.menuButton.getSize()),
                () -> assertEquals(0, AppMenuFrame.menuButton.getX()),
                () -> assertEquals(0, AppMenuFrame.menuButton.getY()),
                () -> assertEquals(false, AppMenuFrame.menuButton.isFocusable()),

                () -> assertEquals("Contact", AppMenuFrame.contactButton.getText()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 15), AppMenuFrame.contactButton.getFont()),
                () -> assertEquals(new Dimension(100,50), AppMenuFrame.contactButton.getSize()),
                () -> assertEquals(100, AppMenuFrame.contactButton.getX()),
                () -> assertEquals(0, AppMenuFrame.contactButton.getY()),
                () -> assertEquals(false, AppMenuFrame.contactButton.isFocusable())
        );

    }


}
