package org.restaurantfis.sre.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationFrameTest {




    @Test
    public void checkTimeIntervals(){

        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0); // using table 1 just for testing
        frame.dispose();

        Assertions.assertAll(
                () -> assertEquals("08AM-10AM", frame.timeIntervals[0].getText()),
                () -> assertEquals("02PM-04PM", frame.timeIntervals[1].getText()),
                () -> assertEquals("10AM-12AM", frame.timeIntervals[2].getText()),
                () -> assertEquals("04PM-06PM", frame.timeIntervals[3].getText()),
                () -> assertEquals("12AM-02PM", frame.timeIntervals[4].getText()),
                () -> assertEquals("06PM-08PM", frame.timeIntervals[5].getText())
                );
    }

    @Test
    public void setTransversalLines(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0);
        frame.dispose();

        JPanel testPanel = new JPanel();
        frame.setTransversalLines(testPanel, 50);

        Assertions.assertAll(
                () -> assertEquals(Color.black, testPanel.getBackground()),
                () -> assertEquals(new Dimension(800,3), testPanel.getSize()),
                () -> assertEquals(0, testPanel.getX()),
                () -> assertEquals(50, testPanel.getY())
        );
    }

    @Test
    public void setReservationButtons(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0);
        frame.dispose();

        JButton testButton = new JButton();
        frame.setReservationButtons(testButton, 70,80);

        Assertions.assertAll(
                () -> assertEquals(new Dimension(103,109), testButton.getSize()),
                () -> assertEquals(70, testButton.getX()),
                () -> assertEquals(80, testButton.getY()),
                () -> assertTrue(testButton.getActionListeners().length > 0),
                () -> assertEquals(new Color(0,82,33), testButton.getBackground()),
                () -> assertFalse(testButton.isFocusable()),
                () -> assertEquals(null, testButton.getBorder())
        );

    }

    @Test
    public void setTimeLabels(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0);
        frame.dispose();

        JLabel testLabel = new JLabel();
        frame.setTimeLabels(testLabel, "testHour", 100,33);

        Assertions.assertAll(
                () -> assertEquals("testHour", testLabel.getText()),
                () -> assertEquals(new Font("Arial", Font.PLAIN, 30), testLabel.getFont()),
                () -> assertEquals(Color.black, testLabel.getForeground()),
                () -> assertEquals(new Dimension(200, 30), testLabel.getSize()),
                () -> assertEquals(100, testLabel.getX()),
                () -> assertEquals(33, testLabel.getY())
        );
    }

    @Test
    public void enableReservationButtons(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0);
        frame.dispose();

        frame.enableReservationButtons();

        Assertions.assertAll(
                () -> assertTrue(frame.reservationButtons[0].isEnabled()),
                () -> assertTrue(frame.reservationButtons[1].isEnabled()),
                () -> assertTrue(frame.reservationButtons[2].isEnabled()),
                () -> assertTrue(frame.reservationButtons[3].isEnabled()),
                () -> assertTrue(frame.reservationButtons[4].isEnabled()),
                () -> assertTrue(frame.reservationButtons[5].isEnabled())
        );
    }

    @Test
    public void enableReservedLabel(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0); // using table 1 just for testing
        frame.dispose();

        JLabel testLabel = new JLabel();
        frame.enableReservedLabel(testLabel, 30, 31);

        Assertions.assertAll(
                () -> assertEquals(new Font("Arial", Font.PLAIN, 25), testLabel.getFont()),
                () -> assertEquals(Color.black, testLabel.getForeground()),
                () -> assertEquals(new Dimension(120, 50), testLabel.getSize()),
                () -> assertEquals(30, testLabel.getX()),
                () -> assertEquals(31, testLabel.getY()),
                () -> assertFalse(testLabel.isVisible())
        );

    }

    @Test
    public void setDeleteButtons(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserService.validateLogin("daci@", "123");
        ReservationFrame frame = new ReservationFrame(0); // using table 1 just for testing
        frame.dispose();

        JButton testButton = new JButton();
        frame.setDeleteButtons(testButton, 42,32);

        Assertions.assertAll(
                () -> assertEquals(new Dimension(50,50), testButton.getSize()),
                () -> assertEquals(42, testButton.getX()),
                () -> assertEquals(32, testButton.getY()),
                () -> assertFalse(testButton.isFocusable()),
                () -> assertEquals(null, testButton.getBorder()),
                () -> assertTrue(testButton.getActionListeners().length > 0)
        );

    }
}
