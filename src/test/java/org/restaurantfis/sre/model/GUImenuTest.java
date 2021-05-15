package org.restaurantfis.sre.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GUImenuTest {
    @Test
    public void importDataFromFiles(){

        GUImenu frame = new GUImenu();
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

}
