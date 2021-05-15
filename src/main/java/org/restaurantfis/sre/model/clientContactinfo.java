package org.restaurantfis.sre.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.mongodb.*;
import org.restaurantfis.sre.services.UserService;

public class clientContactinfo extends JFrame
{
    public JLabel page_title ;
    public static Container c ;

    public JTable clients;
    public static DefaultTableModel model ;
    public static JScrollPane pane ;
    public Object[] columns_name = {"Name","Email","Address"};
    public static Object[] row = new Object[3];




    private String name, email, address;



    public clientContactinfo()
    {
        setTitle("Clients Info");
        setBounds(460, 240, 1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane() ;
        c.setBackground(new Color(0,82,33));
        c.setLayout(null);

        page_title = new JLabel("Clients info");
        page_title.setForeground(Color.black);
        page_title.setFont(new Font("Arial", Font.BOLD, 40));
        page_title.setSize(500, 50);
        page_title.setLocation(400, 17);
        c.add(page_title);

        model = new DefaultTableModel() ;
        model.setColumnIdentifiers(columns_name);

        clients = new JTable(model);
        clients.getTableHeader().setReorderingAllowed(false);
        clients.setEnabled(false);

        pane = new JScrollPane(clients);
        pane.setBounds(150, 75,700,450);
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
