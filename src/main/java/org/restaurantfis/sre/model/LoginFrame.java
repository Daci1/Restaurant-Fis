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
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setSize(300, 50);
        title.setLocation(370, 30);
        c.add(title);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 30));
        email.setSize(100, 30);
        email.setLocation(280, 140);
        c.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 25));
        temail.setSize(250, 30);
        temail.setLocation(380, 140);
        temail.setBorder(null);
        c.add(temail);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 30));
        pass.setSize(150, 30);
        pass.setLocation(225, 190);
        c.add(pass);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 25));
        tpass.setSize(250, 30);
        tpass.setLocation(380, 190);
        tpass.setBorder(null);
        c.add(tpass);

        log_in = new JButton("Log In");
        log_in.setFont(new Font("Arial", Font.PLAIN, 25));
        log_in.setSize(200, 40);
        log_in.setLocation(220, 340);
        log_in.addActionListener(this);
        log_in.setFocusable(false);
        c.add(log_in);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 25));
        register.setSize(200, 40);
        register.setLocation(460, 340);
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
//            new RegistrationFrame();
            dispose();
        }
    }
}
