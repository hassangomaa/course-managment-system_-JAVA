/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import Home_pages.Home;
import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;


/**
 *
 * @author egypt
 */
public class MainClass {

     
    public static void main(String[] args) {
       //////////////////////////
                Home home = new Home();


       URL url = Home.class.getResource("icon1.png");           
     Image image = Toolkit.getDefaultToolkit().getImage(url);    
                home.setIconImage(image);

        
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }
    
}
