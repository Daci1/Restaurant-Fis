package org.restaurantfis.sre.model;

import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
import org.restaurantfis.sre.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame implements ActionListener {

        // Components of the Form
        private Container c;

        private JLabel title;

        private JLabel name;
        private JTextField tname;

        private JLabel email;
        private JTextField temail;

        private JLabel pass;
        private JPasswordField tpass;

        private JLabel mno;
        private JTextField tmno;

        private JLabel gender;
        private JRadioButton male;
        private JRadioButton female;
        private JRadioButton other;
        private ButtonGroup gengp;

        private JLabel dob;
        private JComboBox date;
        private JComboBox month;
        private JComboBox year;

        private JLabel add;
        private JTextArea tadd;

        private JCheckBox term;

        private JButton sub;
        private JButton reset;

        private JLabel res;

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
        private String years[]
                = {
                "1950", "1951", "1952", "1953",
                "1954", "1955", "1956", "1957",
                "1958", "1959", "1960", "1961",
                "1962", "1963", "1964", "1965",
                "1966", "1967", "1968", "1969",
                "1970", "1971", "1972", "1973",
                "1974", "1975", "1976", "1977",
                "1978", "1979", "1980", "1981",
                "1982", "1983", "1984", "1985",
                "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993",
                "1994", "1995", "1996", "1997",
                "1998", "1999", "2000", "2001",
                "2002", "2003"};

        // constructor, to initialize the components
        // with default values.
        public RegistrationFrame()
        {
            setTitle("Registration Form");
            setBounds(300, 90, 900, 600);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);

            c = getContentPane();
            c.setBackground(Color.GRAY);
            c.setLayout(null);

            title = new JLabel("Registration Form");
            title.setFont(new Font("Arial", Font.PLAIN, 30));
            title.setSize(300, 40);
            title.setLocation(300, 30);
            c.add(title);

            name = new JLabel("Name");
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            name.setSize(100, 20);
            name.setLocation(100, 100);
            c.add(name);

            tname = new JTextField();
            tname.setFont(new Font("Arial", Font.PLAIN, 15));
            tname.setSize(190, 20);
            tname.setLocation(200, 100);
            tname.setBorder(null);
            c.add(tname);

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

            mno = new JLabel("Mobile");
            mno.setFont(new Font("Arial", Font.PLAIN, 20));
            mno.setSize(100, 20);
            mno.setLocation(440, 150);
            c.add(mno);

            tmno = new JTextField();
            tmno.setFont(new Font("Arial", Font.PLAIN, 15));
            tmno.setSize(190, 20);
            tmno.setLocation(540, 150);
            tmno.setBorder(null);
            c.add(tmno);

            gender = new JLabel("Gender");
            gender.setFont(new Font("Arial", Font.PLAIN, 20));
            gender.setSize(100, 20);
            gender.setLocation(100, 200);
            c.add(gender);

            male = new JRadioButton("Male");
            male.setFont(new Font("Arial", Font.PLAIN, 15));
            male.setSelected(true);
            male.setSize(65, 20);
            male.setLocation(200, 200);
            male.setFocusable(false);
            c.add(male);

            female = new JRadioButton("Female");
            female.setFont(new Font("Arial", Font.PLAIN, 15));
            female.setSelected(false);
            female.setSize(80, 20);
            female.setLocation(265, 200);
            female.setFocusable(false);
            c.add(female);


            other = new JRadioButton("Other");
            other.setFont(new Font("Arial", Font.PLAIN, 15));
            other.setSelected(false);
            other.setSize(70, 20);
            other.setLocation(265+75+5, 200);
            other.setFocusable(false);
            c.add(other);

            gengp = new ButtonGroup();
            gengp.add(male);
            gengp.add(female);
            gengp.add(other);

            dob = new JLabel("DOB");
            dob.setFont(new Font("Arial", Font.PLAIN, 20));
            dob.setSize(100, 20);
            dob.setLocation(100, 250);
            c.add(dob);

            date = new JComboBox(dates);
            date.setFont(new Font("Arial", Font.PLAIN, 15));
            date.setSize(50, 20);
            date.setLocation(200, 250);
            c.add(date);

            month = new JComboBox(months);
            month.setFont(new Font("Arial", Font.PLAIN, 15));
            month.setSize(60, 20);
            month.setLocation(250, 250);
            c.add(month);

            year = new JComboBox(years);
            year.setFont(new Font("Arial", Font.PLAIN, 15));
            year.setSize(60, 20);
            year.setLocation(320, 250);
            c.add(year);

            add = new JLabel("Address");
            add.setFont(new Font("Arial", Font.PLAIN, 20));
            add.setSize(100, 20);
            add.setLocation(100, 300);
            c.add(add);

            tadd = new JTextArea();
            tadd.setFont(new Font("Arial", Font.PLAIN, 15));
            tadd.setSize(200, 75);
            tadd.setLocation(200, 300);
            tadd.setLineWrap(true);
            c.add(tadd);

            term = new JCheckBox("Accept Terms And Conditions.");
            term.setFont(new Font("Arial", Font.PLAIN, 15));
            term.setSize(250, 20);
            term.setLocation(150, 400);
            term.setBackground(Color.gray);
            term.setFocusable(false);
            c.add(term);

            sub = new JButton("Submit");
            sub.setFont(new Font("Arial", Font.PLAIN, 15));
            sub.setSize(100, 20);
            sub.setLocation(150, 450);
            sub.addActionListener(this);
            sub.setFocusable(false);
            c.add(sub);

            reset = new JButton("Reset");
            reset.setFont(new Font("Arial", Font.PLAIN, 15));
            reset.setSize(100, 20);
            reset.setLocation(270, 450);
            reset.addActionListener(this);
            reset.setFocusable(false);
            c.add(reset);

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
            if (e.getSource() == sub) {
                if (term.isSelected()) {

                    String gender;
                    if(this.male.isSelected())
                        gender = "Male";
                    else if(this.female.isSelected())
                        gender = "Female";
                    else
                        gender = "Other";

                    try {
                        UserService.checkExistingUser(this.tname.getText());

                        UserService.addUser(
                                this.tname.getText(),
                                this.temail.getText(),
                                new String(this.tpass.getPassword()),
                                this.tmno.getText(),
                                gender,
                                new Date(this.date.getSelectedIndex() + 1, this.month.getSelectedIndex() + 1, this.year.getSelectedIndex() + 1950),
                                this.tadd.getText()
                                );
                        res.setText("Registration complete, you will be redirected.");
                    }catch(UsernameAlreadyExistsException userAlreadyExist){
                        res.setText("User Already Exists!");
                    }
                    catch (Exception UserServiceException)
                    {
                        System.out.println(UserServiceException);
                    }

                }
                else {
                    res.setText("Please accept the"
                            + " terms & conditions..");
                }
            }

            else if (e.getSource() == reset) {
                String def = "";
                tname.setText(def);
                temail.setText(def);
                tpass.setText(def);
                tadd.setText(def);
                tmno.setText(def);
                res.setText(def);
                term.setSelected(false);
                date.setSelectedIndex(0);
                month.setSelectedIndex(0);
                year.setSelectedIndex(0);
            }
        }
    }
