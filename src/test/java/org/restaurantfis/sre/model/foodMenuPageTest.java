package org.restaurantfis.sre.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class foodMenuPageTest {

    @Test
    public void importDataFromFiles(){

        foodMenuPage frame = new foodMenuPage();
        frame.dispose();
        String filePath = "src/main/resources/menuModificationstorage.txt";
        File file = new File(filePath);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Object[] lines = br.lines().toArray();

            for(int i = 0; i < lines.length; i++){
                for(int j = 0; j < frame.menu.getColumnCount(); j++){
                    String[] row = lines[i].toString().split(" ");
                    assertTrue(row[j].equals(frame.menu.getValueAt(i, j).toString()));
                }
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println("data not imported");
        }

    }

    @Test
    public void addToFoodMenu(){
        foodMenuPage frame = new foodMenuPage();
        frame.dispose();
        frame.row[0] = "testFood";
        frame.row[1] = "testFoodPrice";
        frame.row[2] = "testDrink";
        frame.row[3] = "testDrinkPrice";
        frame.model.addRow(frame.row);

        int lastRow = frame.model.getRowCount() -1;
        Assertions.assertAll(
                () -> assertEquals("testFood", frame.menu.getValueAt(lastRow, 0)),
                () -> assertEquals("testFoodPrice", frame.menu.getValueAt(lastRow, 1)),
                () -> assertEquals("testDrink", frame.menu.getValueAt(lastRow, 2)),
                () -> assertEquals("testDrinkPrice", frame.menu.getValueAt(lastRow, 3))
                );

    }

    @Test
    public void deleteFromFoodMenu(){
        foodMenuPage frame = new foodMenuPage();
        frame.dispose();
        String[] beforeAdding = {frame.menu.getValueAt(frame.menu.getRowCount() - 1, 0) + "",
                frame.menu.getValueAt(frame.menu.getRowCount()- 1, 1) + "",
                frame.menu.getValueAt(frame.menu.getRowCount() - 1, 2) + "",
                frame.menu.getValueAt(frame.menu.getRowCount() - 1, 3) + ""};

        frame.row[0] = "testFood";
        frame.row[1] = "testFoodPrice";
        frame.row[2] = "testDrink";
        frame.row[3] = "testDrinkPrice";

        frame.model.addRow(frame.row);

        frame.model.removeRow(frame.menu.getRowCount()-1);

        Assertions.assertAll(
                () -> assertEquals(beforeAdding[0], frame.menu.getValueAt(frame.menu.getRowCount()-1, 0)),
                () -> assertEquals(beforeAdding[1], frame.menu.getValueAt(frame.menu.getRowCount()-1, 1)),
                () -> assertEquals(beforeAdding[2], frame.menu.getValueAt(frame.menu.getRowCount()-1, 2)),
                () -> assertEquals(beforeAdding[3], frame.menu.getValueAt(frame.menu.getRowCount()-1, 3))
        );
    }

    @Test
    public void enableButtons(){
        foodMenuPage frame = new foodMenuPage();
        frame.dispose();

        frame.edit_button.doClick();
        Assertions.assertAll(
                () -> assertTrue(frame.food.isVisible()),
                () -> assertTrue(frame.food_price.isVisible()),
                () -> assertTrue(frame.drinks.isVisible()),
                () -> assertTrue(frame.drinks_price.isVisible())
        );
    }
}
