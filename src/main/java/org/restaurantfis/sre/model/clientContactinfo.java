package org.restaurantfis.sre.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class clientContactinfo extends JFrame
{
    private JLabel page_title ;
    private Container c ;

    private JTable clients;
    private DefaultTableModel model ;
    private JScrollPane pane ;
    private Object[] columns_name = {"Name","Email","Address"};



    public clientContactinfo()
    {
        setTitle("Clients info");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane() ;
        c.setBackground(Color.gray);
        c.setLayout(null);

        page_title = new JLabel("Clients info");
        page_title.setFont(new Font("Arial", Font.BOLD, 40));
        page_title.setSize(500, 50);
        page_title.setLocation(350, 30);
        c.add(page_title);

        model = new DefaultTableModel() ;
        model.setColumnIdentifiers(columns_name);
        clients = new JTable(model);
        pane = new JScrollPane(clients);
        pane.setBounds(175, 200,550,200);
        c.add(pane);


        setVisible(true);

    }

}
