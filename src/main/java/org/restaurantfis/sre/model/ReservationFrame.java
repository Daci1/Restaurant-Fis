package org.restaurantfis.sre.model;

import org.restaurantfis.sre.exceptions.ReservationsLimitReached;
import org.restaurantfis.sre.services.ReservationService;
import org.restaurantfis.sre.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationFrame extends JFrame implements ActionListener {

    private static Container c;

    private static JPanel[] transversalLines;

    private static JButton[] reservationButtons;

    private static JLabel[] timeIntervals;

    private JComboBox reservationDate;

    private LocalDate currentDate;

    private int currentMonthIndex;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };

    private String months[]
            = { "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sep", "Oct", "Nov", "Dec" };

    private int tableNumber;

    public ReservationFrame(int tableNumber){
        this.tableNumber = tableNumber + 1;
        ReservationService.initializeDB();

        setTitle("Reservation for table " + tableNumber);
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

        setButtons(reservationButtons[0], 277, 123);
        setButtons(reservationButtons[1], 661, 123);
        setButtons(reservationButtons[2], 277, 275);
        setButtons(reservationButtons[3], 661, 275);
        setButtons(reservationButtons[4], 277, 427);
        setButtons(reservationButtons[5], 661, 427);

        timeIntervals = new JLabel[6];

        timeIntervals[0] = new JLabel("08AM-10AM");
        setTimeLabels(timeIntervals[0], 50, 161);

        timeIntervals[1] = new JLabel("02PM-04PM");
        setTimeLabels(timeIntervals[1], 453, 161);

        timeIntervals[2] = new JLabel("10AM-12AM");
        setTimeLabels(timeIntervals[2], 50, 316);

        timeIntervals[3] = new JLabel("04PM-06PM");
        setTimeLabels(timeIntervals[3], 453, 316);

        timeIntervals[4] = new JLabel("12AM-02PM");
        setTimeLabels(timeIntervals[4], 50, 466);

        timeIntervals[5] = new JLabel("06PM-08PM");
        setTimeLabels(timeIntervals[5], 453, 466);

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
        reservationDate.addActionListener(this);
        reservationDate.setSelectedIndex(currentDate.getDayOfMonth() - 1);
        c.add(reservationDate);

        updateButtons();
        setVisible(true);
    }

    private void setTransversalLines(JPanel panel, int yPos){
        panel.setBackground(Color.black);
        panel.setBounds(0, yPos,800, 3);
        c.add(panel);
    }

    private void setButtons(JButton button, int xPos, int yPos){
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

    private void setTimeLabels(JLabel label, int xPos, int yPos){
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(Color.black);
        label.setSize(200, 30);
        label.setLocation(xPos, yPos);
        c.add(label);
    }

    private void enableReservationButtons(){
        for(int i = 0; i < 6; i++) reservationButtons[i].setEnabled(true);
    }

    private void updateButtons(){
        enableReservationButtons();
        for(int i = 0; i < 6; i++){
            if(ReservationService.isReserved("Table" + this.tableNumber,
                    timeIntervals[i].getText(),
                    reservationDate.getSelectedIndex() + 1,
                    currentMonthIndex + 1)){
                reservationButtons[i].setEnabled(false);
            }
        }
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
        }
    }
}
