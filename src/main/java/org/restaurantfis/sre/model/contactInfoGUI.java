package org.restaurantfis.sre.model;

import javax.swing.*;
import java.awt.*;

public class contactInfoGUI extends JFrame
{
    private Container contact_info_page ;
    private JLabel contact_info_title;

    private JLabel adress ;
    private JLabel phone_number;
    private JLabel social_media ;
    private JList staff ;
    private String staff_list[] = new String[6];
    private JLabel restaurant_staff ;


    public contactInfoGUI()
    {
        setTitle("Contact Information");
        setVisible(true);
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        contact_info_page = getContentPane();
        contact_info_page.setBackground(Color.gray);
        contact_info_page.setSize(500, 500);
        contact_info_page.setLayout(null);

        contact_info_title = new JLabel("Contact Information") ;
        contact_info_title.setFont(new Font("Arial",Font.PLAIN, 30));
        contact_info_title.setForeground(Color.black);
        contact_info_title.setSize(500, 40);
        contact_info_title.setLocation(175,30);
        contact_info_page.add(contact_info_title);

        adress = new JLabel("Address : Aleea Studentilor, nr 12, 300551");
        adress.setForeground(Color.black);
        adress.setFont(new Font("Arial", Font.PLAIN, 15));
        adress.setLocation(40, 130);
        adress.setSize(500, 40);
        contact_info_page.add(adress);

        phone_number = new JLabel("Phone : 0713457930 / 0748914527 ");
        phone_number.setForeground(Color.black);
        phone_number.setFont(new Font("Arial", Font.PLAIN, 15));
        phone_number.setLocation(40, 165);
        phone_number.setSize(500, 40);
        contact_info_page.add(phone_number);

        social_media = new JLabel("Our official instagram page : restaurant.fis");
        social_media.setForeground(Color.black);
        social_media.setFont(new Font("Arial", Font.PLAIN, 15));
        social_media.setSize(300,40);
        social_media.setLocation(40, 200);
        contact_info_page.add(social_media);


        staff_list[0] = "1.Barbu Dacian - Manager";
        staff_list[1] = "2.Popescu Ion - Chelner";
        staff_list[2] = "3.Andrei Mihai - Bucatar";
        staff_list[3] = "4.Belea Radu - Ajutor Bucatar";
        staff_list[4] = "5.Becali Cristina - Chelnarita";
        staff_list[5] = "6.Dragomir Andrei - Bucatar";

        restaurant_staff = new JLabel("STAFF");
        restaurant_staff.setForeground(Color.black);
        restaurant_staff.setFont(new Font("Arial", Font.PLAIN, 30));
        restaurant_staff.setSize(300,40);
        restaurant_staff.setLocation(80, 265);
        contact_info_page.add(restaurant_staff);


        staff = new JList(staff_list);
        staff.setForeground(Color.black);
        staff.setBackground(Color.gray);
        staff.setFont(new Font("Arial", Font.PLAIN, 15));
        staff.setLocation(40, 320);
        staff.setSize(200, 120);
        staff.setBorder(BorderFactory.createLineBorder(Color.black));
        contact_info_page.add(staff);

        setVisible(true);


    setVisible(true);
    }


}
