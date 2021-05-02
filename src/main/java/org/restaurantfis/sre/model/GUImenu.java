package org.restaurantfis.sre.model;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class GUImenu extends JFrame implements ActionListener {
    private JLabel page_title;
    private Container menu_page;
    private JTable menu;
    private Object[] columns = {"FOOD", "FOOD PRICE", "DRINKS", "DRINKS PRICE"};
    private Object[] row = new Object[4];
    private DefaultTableModel model;
    private JScrollPane pane;
    private JButton  back_button;

    public GUImenu() {
        setTitle("Restaurant Food Menu");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        menu_page = getContentPane();
        menu_page.setBackground(Color.gray);
        menu_page.setLayout(null);

        //titlul
        page_title = new JLabel("Restaurant Food Menu");
        page_title.setFont(new Font("Arial", Font.BOLD, 40));
        page_title.setSize(500, 50);
        page_title.setLocation(250, 30);
        menu_page.add(page_title);
        //tabel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        menu = new JTable(model);
        menu.setEnabled(false);
        ImportdataFromfiles();

        pane = new JScrollPane(menu);
        pane.setBounds(175, 200, 550, 200);
        menu_page.add(pane);

        //back button
        back_button = new JButton("BACK");
        back_button.setFont(new Font("Arial", Font.PLAIN, 15));
        back_button.setBounds(50, 30, 75, 50);
        menu_page.add(back_button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void ImportdataFromfiles()
    {
        String filePath = "src/main/java/org/restaurantfis/sre/model/menuModificationstorage.txt";
        File file = new File(filePath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            DefaultTableModel model = (DefaultTableModel)menu.getModel();
            Object[] lines = br.lines().toArray();

            for(int i = 0; i < lines.length; i++){
                String[] row = lines[i].toString().split(" ");
                model.addRow(row);
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println("something went wrong");
        }
    }

}


