package org.restaurantfis.sre.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AppMenuFrame extends JFrame implements ActionListener {


    private static Container c;

    private static JButton registerButton;
    private static JButton loginButton;
    private static JButton contactButton;
    private static JButton menuButton;

    private static JButton[] tableButtons;


    public AppMenuFrame(){
        setTitle("Restaurant FIS");
        setBounds(300, 90, 1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setBackground(new Color(0,82,33));
        c.setLayout(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.black);
        menuPanel.setBounds(0,50,1200,3);
        c.add(menuPanel);

        JPanel whiteMenuPanel = new JPanel();
        whiteMenuPanel.setBackground(Color.white);
        whiteMenuPanel.setBounds(400,0,1200,49);
        c.add(whiteMenuPanel);


        registerButton = new JButton();
        this.configButton(registerButton, "Register", 0);

        loginButton = new JButton();
        this.configButton(loginButton, "Login", 100);

        contactButton = new JButton();
        this.configButton(contactButton, "Contact", 200);

        menuButton = new JButton();
        this.configButton(menuButton, "Menu", 300);

        tableButtons = new JButton[6];

        for(int i = 0; i < 6; i++) tableButtons[i] = new JButton();

        configTableButton(tableButtons[0], 525,80);
        configTableButton(tableButtons[1], 50,270);
        configTableButton(tableButtons[2], 985,270);
        configTableButton(tableButtons[3], 50,470);
        configTableButton(tableButtons[4], 985,470);
        configTableButton(tableButtons[5], 525,580);




        setVisible(true);
    }

    public void configButton(JButton b, String buttonName, int xPos){
        b.setText(buttonName);
        b.setFont(new Font("Arial", Font.PLAIN, 15));
        b.setSize(100, 50);
        b.setLocation(xPos, 0);
        b.addActionListener(this);
        b.setFocusable(false);
        c.add(b);
    }

    public void configTableButton(JButton b, int xPos, int yPos){

        ImageIcon tableIcon = new ImageIcon("src/main/java/photos/restaurantTable.png");
        b.setBounds(xPos,yPos,150,150);
        b.setFocusable(false);
        b.setIcon(tableIcon);
        b.addActionListener(this);
        b.setBorder(null);
        c.add(b);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == registerButton){
            new RegistrationFrame();
        }

        if(e.getSource() == loginButton){
            new LoginFrame();
        }

        if(e.getSource() == contactButton){
            System.out.println("TODO: Contact");
        }

        if(e.getSource() == menuButton){
            System.out.println("TODO: Menu");
        }

        for(int i = 0; i < 6; i++){ //Just for testing
            if(e.getSource() == tableButtons[i]){
                System.out.println(i);
                break;
            }
        }


    }



}
