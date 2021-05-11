package org.restaurantfis.sre.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.mongodb.*;
import org.restaurantfis.sre.services.UserService;

public class clientContactinfo extends JFrame
{
    private JLabel page_title ;
    private static Container c ;

    private JTable clients;
    private static DefaultTableModel model ;
    private static JScrollPane pane ;
    private Object[] columns_name = {"Name","Email","Address"};
    private static Object[] row = new Object[3];




    private String name, email, address;



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
        clients.getTableHeader().setReorderingAllowed(false);
        clients.setEnabled(false);

        pane = new JScrollPane(clients);
        pane.setBounds(175, 200,550,200);
        c.add(pane);
        addTotable();

        setVisible(true);

    }

    public static void addRow(String name, String email, String address)
    {
        row[0] = name;
        row[1] = email;
        row[2] = address ;
        model.addRow(row);
    }

    public static void addTotable()
    {
        DBCursor cursor = UserService.getUsersCollection().find();
        while(cursor.hasNext())
        {
            DBObject obj = cursor.next();
            clientContactinfo.addRow((String)obj.get("name"), (String)obj.get("email"), (String)obj.get("address"));

        }

    }




}
