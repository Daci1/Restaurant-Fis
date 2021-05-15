package org.restaurantfis.sre.model;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.restaurantfis.sre.services.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormatSymbols;

public class ReservationlistFrame extends JFrame {

    public static Container c;
    public static JScrollPane pane;

    public Object[] columns = {"User Name", "Table", "Reservataion Hour", "Reservation Day", "Reservation Month"};
    public static Object[] row = new Object[5];
    public static DefaultTableModel model;
    public JTable menu ;
    public JLabel titleLabel;

    public ReservationlistFrame()
    {
        setTitle("Reservation List");
        setBounds(460, 240, 1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setBackground(new Color(0,82,33));
        c.setLayout(null);

        titleLabel = new JLabel("List of the reservations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.black);
        titleLabel.setSize(800 ,40);
        titleLabel.setLocation(250 ,17);
        c.add(titleLabel);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        menu = new JTable(model);
        menu.getTableHeader().setReorderingAllowed(false);

        //disable editing and row selecting
        menu.setDefaultEditor(Object.class, null);
        menu.setFocusable(false);
        menu.setRowSelectionAllowed(false);

        pane = new JScrollPane(menu);
        pane.setBounds(150, 75,700,450);

        c.add(pane);

        populateReservationList();



        this.setVisible(true);
    }


    public static void addRow(String userName, String tableName, String reservationHour, int reservationDay, int reservationMonth)
    {
        row[0] = userName;
        row[1] = tableName;
        row[2] = reservationHour;
        row[3] = reservationDay;
        row[4] = new DateFormatSymbols().getMonths()[reservationMonth-1];
        model.addRow(row);
    }

    public static void populateReservationList()
    {
        DBCursor cursor = ReservationService.getTablesCollection().find();
        while(cursor.hasNext())
        {
            DBObject currentDBObject = cursor.next();
            ReservationlistFrame.addRow((String)currentDBObject.get("userName"),
                    (String)currentDBObject.get("tableName"),
                    (String)currentDBObject.get("reservationHour"),
                    (int)currentDBObject.get("reservationDay"),
                    (int)currentDBObject.get("reservationMonth"));
        }
    }

}
