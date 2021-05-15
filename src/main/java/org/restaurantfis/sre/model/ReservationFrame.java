package org.restaurantfis.sre.model;

import org.restaurantfis.sre.exceptions.ReservationsLimitReached;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReservationFrame extends JFrame implements ActionListener {

    public static Container c;

    public static JPanel[] transversalLines;

    public static JButton[] reservationButtons;

    public static JLabel[] timeIntervals;

    public JComboBox reservationDate;

    public LocalDate currentDate;

    public int currentMonthIndex;

    public JLabel[] reservedLabels;

    public JButton[] deleteReservationButtons;

    public String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };

    public String months[]
            = { "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sep", "Oct", "Nov", "Dec" };

    public int tableNumber;

    public ReservationFrame(int tableNumber){
        this.tableNumber = tableNumber + 1;

        setTitle("Reservation for table " + this.tableNumber);
        setBounds(900, 100, 800, 594);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setBackground(new Color(0,82,33));
        c.setLayout(null);

        JPanel middleLine = new JPanel();
        middleLine.setBackground(Color.black);
        middleLine.setBounds(400, 100, 3, 500);
        c.add(middleLine);

        transversalLines = new JPanel[3];
        for(int i = 0; i < 3; i++) transversalLines[i] = new JPanel();
        this.setTransversalLines(transversalLines[0], 100);
        this.setTransversalLines(transversalLines[1], 252);
        this.setTransversalLines(transversalLines[2], 404);

        reservationButtons = new JButton[6];
        for(int i = 0; i < 6; i++) reservationButtons[i] = new JButton();

        setReservationButtons(reservationButtons[0], 277, 123);
        setReservationButtons(reservationButtons[1], 661, 123);
        setReservationButtons(reservationButtons[2], 277, 275);
        setReservationButtons(reservationButtons[3], 661, 275);
        setReservationButtons(reservationButtons[4], 277, 427);
        setReservationButtons(reservationButtons[5], 661, 427);

        timeIntervals = new JLabel[6];
        for(int i = 0; i < 6; i++) timeIntervals[i] = new JLabel();

        setTimeLabels(timeIntervals[0], "08AM-10AM", 50, 161);
        setTimeLabels(timeIntervals[1], "02PM-04PM", 453, 161);
        setTimeLabels(timeIntervals[2], "10AM-12AM", 50, 316);
        setTimeLabels(timeIntervals[3], "04PM-06PM", 453, 316);
        setTimeLabels(timeIntervals[4], "12AM-02PM", 50, 466);
        setTimeLabels(timeIntervals[5], "06PM-08PM", 453, 466);

        currentDate = LocalDate.now();
        currentMonthIndex = currentDate.getMonthValue() - 1;

        JLabel selectDateLabel = new JLabel("Select your reservation Date:");
        selectDateLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        selectDateLabel.setForeground(Color.black);
        selectDateLabel.setSize(400, 30);
        selectDateLabel.setLocation(20, 40);
        c.add(selectDateLabel);

        reservationDate = new JComboBox(dates);
        reservationDate.setFont(new Font("Arial", Font.PLAIN, 20));
        reservationDate.setSize(60, 30);
        reservationDate.setLocation(420, 42);
        reservationDate.setSelectedIndex(currentDate.getDayOfMonth() - 1);
        reservationDate.addActionListener(this);
        c.add(reservationDate);

        reservedLabels = new JLabel[6];
        for(int i = 0; i < 6; i++) reservedLabels[i] = new JLabel("Reserved");
        enableReservedLabel(reservedLabels[0], 247, 123);
        enableReservedLabel(reservedLabels[1], 640, 123);
        enableReservedLabel(reservedLabels[2], 247, 278);
        enableReservedLabel(reservedLabels[3], 640, 278);
        enableReservedLabel(reservedLabels[4], 247, 428);
        enableReservedLabel(reservedLabels[5], 640, 428);

        deleteReservationButtons = new JButton[6];
        for(int i = 0; i < 6; i++) deleteReservationButtons[i] = new JButton();

        setDeleteButtons(deleteReservationButtons[0], 280,180);
        setDeleteButtons(deleteReservationButtons[1], 673,180);
        setDeleteButtons(deleteReservationButtons[2], 280,335);
        setDeleteButtons(deleteReservationButtons[3], 673,335);
        setDeleteButtons(deleteReservationButtons[4], 280,485);
        setDeleteButtons(deleteReservationButtons[5], 673,485);

        updateButtons();

        setVisible(true);
    }

    public void setTransversalLines(JPanel panel, int yPos){
        panel.setBackground(Color.black);
        panel.setBounds(0, yPos,800, 3);
        c.add(panel);
    }

    public void setReservationButtons(JButton button, int xPos, int yPos){
        ImageIcon checkIcon = new ImageIcon("src/main/resources/photos/checkButton.png");

        button.setSize(103, 109);
        button.setLocation(xPos, yPos);
        button.addActionListener(this);
        button.setBackground(new Color(0,82,33));
        button.setFocusable(false);
        button.setBorder(null);
        button.setIcon(checkIcon);
        c.add(button);
    }

    public void setTimeLabels(JLabel label, String text, int xPos, int yPos){
        label.setText(text);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(Color.black);
        label.setSize(200, 30);
        label.setLocation(xPos, yPos);
        c.add(label);
    }

    public void enableReservationButtons(){
        for(int i = 0; i < 6; i++) reservationButtons[i].setEnabled(true);
    }

    public void updateButtons(){
        enableReservationButtons();
        for(int i = 0; i < 6; i++){
            if(ReservationService.isReserved("Table" + this.tableNumber,
                    timeIntervals[i].getText(),
                    reservationDate.getSelectedIndex() + 1,
                    currentMonthIndex + 1)){
                reservationButtons[i].setVisible(false);
                reservedLabels[i].setVisible(true);
                //check if the actual user is also the one who reserved the table
                if(ReservationService.checkReservation("Table" + this.tableNumber,
                        timeIntervals[i].getText(),
                        reservationDate.getSelectedIndex() + 1,
                        currentMonthIndex + 1)){
                    deleteReservationButtons[i].setVisible(true);
                }
            }
            else{
                reservationButtons[i].setVisible(true);
                reservedLabels[i].setVisible(false);
                deleteReservationButtons[i].setVisible(false);
            }
        }
    }

    public void enableReservedLabel(JLabel label, int xPos, int yPos){
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setForeground(Color.black);
        label.setSize(120, 50);
        label.setLocation(xPos, yPos);
        label.setVisible(false);
        c.add(label);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == reservationDate){
            enableReservationButtons();

            if(reservationDate.getSelectedIndex() + 1 < currentDate.getDayOfMonth()){

                JOptionPane.showMessageDialog(null, "You are going to make the reservation for the upcoming month!", "Warning!", JOptionPane.WARNING_MESSAGE);
                currentMonthIndex++;
            }else{
                currentMonthIndex = currentDate.getMonthValue() - 1;
            }
            currentMonthIndex = currentMonthIndex % 12;
            updateButtons();

        }

        for(int i = 0; i < 6; i++){
            if(e.getSource() == reservationButtons[i]){
                try{
                    ReservationService.addReservation("Table" + this.tableNumber,
                            timeIntervals[i].getText(),
                            reservationDate.getSelectedIndex() + 1,
                            currentMonthIndex + 1,
                            UserService.loggedUser.getName());
                    updateButtons();
                }catch(ReservationsLimitReached reservationLimitException){
                    JOptionPane.showMessageDialog(null, reservationLimitException, "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }

            if(e.getSource() == deleteReservationButtons[i]){
                ReservationService.deleteReservation("Table" + this.tableNumber,
                        timeIntervals[i].getText(),
                        reservationDate.getSelectedIndex() + 1,
                        currentMonthIndex + 1);
                updateButtons();
                JOptionPane.showMessageDialog(null, "You just deleted your reservation!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void setDeleteButtons(JButton button, int xPos, int yPos){
        ImageIcon deleteIcon = new ImageIcon("src/main/resources/photos/deleteButton.jpg");

        button.setSize(50,50);
        button.setLocation(xPos, yPos);
        button.setFocusable(false);
        button.setBorder(null);
        button.setVisible(false);
        button.setIcon(deleteIcon);
        button.addActionListener(this);
        c.add(button);
    }
}
