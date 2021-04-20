package org.restaurantfis.sre.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;

    private JLabel title;

    private JLabel email;
    private JTextField temail;

    private JLabel pass;
    private JPasswordField tpass;

    private JButton log_in;
    private JButton register;

    private JLabel res;

    // constructor, to initialize the components
    // with default values.

    public static void main(String args[])
    {
        new LoginFrame();
    }
    public LoginFrame()
    {
        setTitle("Login Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setBackground(Color.GRAY);
        c.setLayout(null);

        title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(300, 30);
        c.add(title);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(440, 100);
        c.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(540, 100);
        temail.setBorder(null);
        c.add(temail);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(100, 150);
        c.add(pass);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(190, 20);
        tpass.setLocation(200, 150);
        tpass.setBorder(null);
        c.add(tpass);

        log_in = new JButton("Log In");
        log_in.setFont(new Font("Arial", Font.PLAIN, 15));
        log_in.setSize(100, 20);
        log_in.setLocation(150, 450);
        log_in.addActionListener(this);
        log_in.setFocusable(false);
        c.add(log_in);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(270, 450);
        register.addActionListener(this);
        register.setFocusable(false);
        c.add(register);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);


        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == log_in) {
            res.setText("Log In completed, you will be redirected.");
        }

        else if (e.getSource() == register) {
            new RegistrationFrame();
            dispose();
        }
    }
}
