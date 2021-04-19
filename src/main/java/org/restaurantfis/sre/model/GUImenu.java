package org.restaurantfis.sre.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUImenu extends JFrame
{

   private Container menu_page ;
   private JLabel title ;

   private String pizza_list[] = new String[8] ;
   private JList pizza ;
   private JLabel pizza_title ;

   private String pasta_list[] = new String[5];
   private JList pasta ;
   private JLabel pasta_title ;

   private String drinks_list[] = new String[15];
   private JList drinks;
   private JLabel drinks_title ;





   public GUImenu()
   {
      setTitle("Restaurant Food Menu");
      setBounds(300, 90, 900, 600);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);

      menu_page = getContentPane();
      menu_page.setBackground(Color.gray);
      menu_page.setLayout(null);

      title = new JLabel("Restaurant Food Menu");
      title.setFont(new Font("Arial", Font.PLAIN, 30));
      title.setSize(500, 40);
      title.setLocation(300, 30);
      title.setForeground(Color.black);
      menu_page.add(title);

      pizza_title = new JLabel("PIZZA");
      pizza_title.setFont(new Font("Arial", Font.PLAIN, 25));
      pizza_title.setSize(300, 40);
      pizza_title.setLocation(80, 100);
      menu_page.add(pizza_title);

      pizza_list[0] = "1.Pepperoni Pizza......10$";
      pizza_list[1] = "2.Meat Pizza..............12$";
      pizza_list[2] = "3.Margherita Pizza.....11$";
      pizza_list[3] = "4.Hawaiian Pizza....10.5$";
      pizza_list[4] = "5.Buffalo Pizza...........20$";
      pizza_list[5] = "6.Supreme Pizza..........4$";
      pizza_list[6] = "7.Cheese Pizza..........14$";
      pizza_list[7] = "8.Veggie Pizza...........30$";

      pizza = new JList(pizza_list);
      pizza.setVisibleRowCount(8);
      pizza.setFont(new Font("Arial", Font.PLAIN, 15));
      pizza.setSize(100,20);
      pizza.setBounds(40,150,180,160);
      pizza.setBackground(Color.gray);
      menu_page.add(pizza);

      pasta_title = new JLabel("PASTA");
      pasta_title.setFont(new Font("Arial", Font.PLAIN, 25));
      pasta_title.setSize(300, 40);
      pasta_title.setLocation(80, 325);
      menu_page.add(pasta_title);

      pasta_list[0] = "1.Pasta Con Pomodoro....12$";
      pasta_list[1] = "2.Raviolli...........................14$";
      pasta_list[2] = "3.Spagetti Bolognese.........9$";
      pasta_list[3] = "4.Lasagna......................18.5$";
      pasta_list[4] = "5.Penne Ala Vodka...........16$";


      pasta = new JList(pasta_list);
      pasta.setVisibleRowCount(5);
      pasta.setFont(new Font("Arial", Font.PLAIN, 15));
      pasta.setSize(100,20);
      pasta.setBounds(40,375,200,160);
      pasta.setBackground(Color.gray);
      menu_page.add(pasta);

      drinks_title = new JLabel("Soft & Alcoholic Drinks");
      drinks_title.setFont(new Font("Arial", Font.PLAIN, 25));
      drinks_title.setSize(300, 40);
      drinks_title.setLocation(500, 100);
      menu_page.add(drinks_title);

      drinks_list[0] ="1.Coca-Cola...................2$";
      drinks_list[1] ="2.Pepsi........................2.5$";
      drinks_list[2] ="3.Sprite...........................3$";
      drinks_list[3] ="4.Water...........................1$";
      drinks_list[4] ="5.Sparkling Water........1.5$";
      drinks_list[5] ="6.RedBull........................5$";
      drinks_list[6] ="7.Hell...............................4$";
      drinks_list[7] ="8.Tuica..........................10$";
      drinks_list[8] ="9.Mona............................9$";
      drinks_list[9] ="10.Vodka.......................15$";
      drinks_list[10] ="11.Saniuta.......................6$";
      drinks_list[11] ="12.Bere............................7$";
      drinks_list[12] ="13.Gin..............................8$";
      drinks_list[13] ="14.Drobeta.....................16$";
      drinks_list[14] ="15.Bourbon.....................20$";

      drinks = new JList(drinks_list);
      drinks.setVisibleRowCount(15);
      drinks.setFont(new Font("Arial", Font.PLAIN, 15));
      drinks.setSize(100,20);
      drinks.setBounds(550,150,200,480);
      drinks.setBackground(Color.gray);
      menu_page.add(drinks);
                      setVisible(true);
   }
}
