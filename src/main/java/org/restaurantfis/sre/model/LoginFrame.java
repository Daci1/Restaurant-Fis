package org.restaurantfis.sre.model;

import org.restaurantfis.sre.exceptions.EmptyEmailException;
import org.restaurantfis.sre.exceptions.EmptyPasswordException;
import org.restaurantfis.sre.exceptions.InvalidLoginCredentialsException;
import org.restaurantfis.sre.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    // Components of the Form
    private static Container c;

    private static JLabel title;

    private static JLabel email;
    private static JTextField temail;

    private static JLabel pass;
    private static JPasswordField tpass;

    private static JButton log_in;
    private static JButton register;

    private JLabel res;

    // constructor, to initialize the components
    // with default values.

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

            try{
                checkCredentials();
                res.setText("Log In completed, you will be redirected.");
            }catch(EmptyEmailException emptyUser) {
                res.setText("Email cannot be empty.");
            }catch (EmptyPasswordException emptyPass){
                res.setText("Password cannot be empty.");
            }catch(InvalidLoginCredentialsException invalidLogin){
                res.setText("Login credentials are invalid.");
            }


        }

        else if (e.getSource() == register) {
            new RegistrationFrame();
            dispose();
        }
    }

    public static void checkCredentials() throws EmptyEmailException, EmptyPasswordException, InvalidLoginCredentialsException {
        if(temail.getText().equals(""))
            throw new EmptyEmailException();

        else if(new String(tpass.getPassword()).equals(""))
            throw new EmptyPasswordException();

        else if(!UserService.validateLogin(temail.getText(), new String(tpass.getPassword())))
            throw new InvalidLoginCredentialsException();

    }
}
